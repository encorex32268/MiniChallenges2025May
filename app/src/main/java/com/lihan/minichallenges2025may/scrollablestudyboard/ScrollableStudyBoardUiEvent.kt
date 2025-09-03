package com.lihan.minichallenges2025may.scrollablestudyboard

sealed interface ScrollableStudyBoardUiEvent {
    data object OnPinLimitWarning: ScrollableStudyBoardUiEvent
    data object OnReachingEndWarning: ScrollableStudyBoardUiEvent
    data object OnScrollToTop: ScrollableStudyBoardUiEvent
    data class OnHeaderAnimateScrollToItem(val headerIndex: Int): ScrollableStudyBoardUiEvent
}