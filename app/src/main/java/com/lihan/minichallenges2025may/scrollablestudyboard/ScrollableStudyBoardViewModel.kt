package com.lihan.minichallenges2025may.scrollablestudyboard

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ScrollableStudyBoardState(
    val lessonTopicList: List<LessonTopic> = lessonTopics
)

fun List<LessonTopic>.groupByCategory() = this.groupBy { it.category }
fun List<LessonTopic>.groupByCategoryMapKey() = this.groupBy { it.category }.map { it.key }
fun List<LessonTopic>.filterPinList() = this.filter { it.isPinned }
fun List<LessonTopic>.filterNotPinList() = this.filter { !it.isPinned }

class ScrollableStudyBoardViewModel : ViewModel(){

    private val _state = MutableStateFlow(ScrollableStudyBoardState())
    val state = _state.asStateFlow()

    fun onAction(action: ScrollableStudyBoardAction){
        when(action){
            is ScrollableStudyBoardAction.OnPinItem -> {
                val filterPined = state.value.lessonTopicList.filterPinList()
                if (filterPined.size == 5 && !action.lessonTopic.isPinned) return
                val newList = state.value.lessonTopicList.map {
                    val isSameTitle = it.title == action.lessonTopic.title
                    val isSameCategory = it.category == action.lessonTopic.category
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

            is ScrollableStudyBoardAction.ToDetail -> Unit
        }
    }


}