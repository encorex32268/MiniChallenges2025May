package com.lihan.minichallenges2025may.studyfeedswitcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.isActive
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

data class StudyFeedSwitcherState(
    val isScrolling: Boolean = false,
    val isShowSwipeHighlights: Boolean = false,
    val stayTime: Long = 0,
    val quickLessonUis: List<QuickLessonUi> = quickLessonUiList,
)

class StudyFeedSwitcherViewModel: ViewModel() {

    private val _state = MutableStateFlow(StudyFeedSwitcherState())
    val state = _state.asStateFlow()

    init {
        Timer.timeEmit().onEach { duration ->
            if (!state.value.isScrolling){
                if (state.value.isShowSwipeHighlights) return@onEach
                val newStayTime = state.value.stayTime + duration.inWholeMilliseconds
                _state.update { it.copy(
                    stayTime = newStayTime,
                    isShowSwipeHighlights = newStayTime >= 3000L
                ) }
            }else{
                _state.update { it.copy(
                    stayTime = 0L,
                    isShowSwipeHighlights = false
                ) }
            }
        }.launchIn(viewModelScope)

    }

    fun onAction(action: StudyFeedSwitcherAction){
        when (action){
            StudyFeedSwitcherAction.OnScrolling -> {
                if (!_state.value.isScrolling){
                    _state.update { it.copy(
                        isScrolling = true
                    ) }
                }
            }
            StudyFeedSwitcherAction.OnStopScrolling -> {
                if (_state.value.isScrolling){
                    _state.update { it.copy(
                        isScrolling = false
                    ) }
                }
            }
        }
    }


}

object Timer{

    fun timeEmit(): Flow<Duration> = flow{
        var currentTime = System.currentTimeMillis()
        while (true){
            delay(200L)
            val lastTime = System.currentTimeMillis()
            val elapsedTime =  lastTime - currentTime
            emit(elapsedTime.milliseconds)
            currentTime = System.currentTimeMillis()
        }
    }
}