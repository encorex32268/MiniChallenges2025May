package com.lihan.minichallenges2025may.searchablestudylist

sealed interface StudyAction {
    data class OnSearchTextChange(val text: String): StudyAction
}