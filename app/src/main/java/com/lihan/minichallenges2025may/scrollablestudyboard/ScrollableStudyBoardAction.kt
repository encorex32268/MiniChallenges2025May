package com.lihan.minichallenges2025may.scrollablestudyboard

sealed interface ScrollableStudyBoardAction {
     data class OnPinItem(val lessonTopic: LessonTopic): ScrollableStudyBoardAction
     data class ToDetail(val lessonTopic: LessonTopic): ScrollableStudyBoardAction
     data object OnScrollEnd: ScrollableStudyBoardAction
     data object OnScrollToTop: ScrollableStudyBoardAction
     data class OnCategoryClick(val category: String): ScrollableStudyBoardAction
}