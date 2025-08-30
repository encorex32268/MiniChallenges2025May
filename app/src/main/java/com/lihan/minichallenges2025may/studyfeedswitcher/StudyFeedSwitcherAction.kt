package com.lihan.minichallenges2025may.studyfeedswitcher

sealed interface StudyFeedSwitcherAction {
    data object OnScrolling: StudyFeedSwitcherAction
    data object OnStopScrolling: StudyFeedSwitcherAction
}