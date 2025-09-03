package com.lihan.minichallenges2025may.scrollablestudyboard

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ScrollableStudyBoardViewModel : ViewModel(){

    private val _state = MutableStateFlow(ScrollableStudyBoardState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<ScrollableStudyBoardUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(action: ScrollableStudyBoardAction){
        when(action){
            is ScrollableStudyBoardAction.ToDetail -> Unit
            is ScrollableStudyBoardAction.OnPinItem -> onPinItem(action.lessonTopic)
            is ScrollableStudyBoardAction.OnCategoryClick -> onCategoryClick(action.category)
            ScrollableStudyBoardAction.OnScrollEnd -> {
                viewModelScope.launch {
                    _uiEvent.send(ScrollableStudyBoardUiEvent.OnReachingEndWarning)
                }
            }
            ScrollableStudyBoardAction.OnScrollToTop -> {
                viewModelScope.launch {
                    _uiEvent.send(ScrollableStudyBoardUiEvent.OnScrollToTop)
                }
            }
        }
    }

    private fun onCategoryClick(category: String){
        val currentState = _state.value
        val hash = currentState.lessonTopicList.getHeaderIndexHashForLazyColumn()
        val headerIndex = if (category == "Science"){
            0
        }else{
            hash.getValue(category)
        }
        viewModelScope.launch {
            _uiEvent.send(
                ScrollableStudyBoardUiEvent.OnHeaderAnimateScrollToItem(headerIndex = headerIndex)
            )
        }
    }

    private fun onPinItem(lessonTopic: LessonTopic){
        val filterPined = state.value.lessonTopicList.filterPinList()
        if (filterPined.size == 5 && !lessonTopic.isPinned) {
            viewModelScope.launch {
                _uiEvent.send(ScrollableStudyBoardUiEvent.OnPinLimitWarning)
            }
            return
        }
        val newList = state.value.lessonTopicList.map {
            val isSameTitle = it.title == lessonTopic.title
            val isSameCategory = it.category == lessonTopic.category
            val isSameItem = isSameTitle && isSameCategory
            if (isSameItem){
                it.copy(isPinned = !it.isPinned)
            }else{
                it
            }
        }
        _state.update { it.copy(
            lessonTopicList = newList
        ) }
    }

}