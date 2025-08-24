@file:OptIn(FlowPreview::class)

package com.lihan.minichallenges2025may.searchablestudylist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class StudyViewModel: ViewModel() {

    private var hasInitial = false

    private val originalItems = studyTopics

    private val _state = MutableStateFlow(
        StudyState(
            items = studyTopics
        )
    )
    val state = _state.onStart {
        if (!hasInitial){
            observeSearchText()
            hasInitial = true
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        StudyState(items = studyTopics)
    )

    fun onAction(action: StudyAction){
        when(action){
            is StudyAction.OnSearchTextChange -> {
                _state.update { it.copy(
                    searchText = action.text
                ) }
            }
        }
    }

    private fun observeSearchText(){
        state
            .map { it.searchText.trim() }
            .debounce(300)
            .distinctUntilChanged()
            .onEach { searchText ->
                val newItems = if (searchText.isEmpty()){
                    originalItems
                }else{
                    originalItems
                        .filter { studyTopic ->
                            studyTopic.title.contains(searchText,ignoreCase = true) ||
                                    studyTopic.subjects.any { it.contains(searchText,ignoreCase = true) }
                        }
                }
                _state.update { it.copy(
                    items = newItems
                ) }

            }
            .launchIn(viewModelScope)
    }


}