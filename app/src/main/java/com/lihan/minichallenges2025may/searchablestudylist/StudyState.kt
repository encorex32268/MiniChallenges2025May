package com.lihan.minichallenges2025may.searchablestudylist

data class StudyState(
    val items: List<StudyTopic> = emptyList(),
    val isLoading: Boolean = false,
    val searchText: String = ""
)
