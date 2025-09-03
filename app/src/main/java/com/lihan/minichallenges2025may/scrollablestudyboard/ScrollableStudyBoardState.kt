package com.lihan.minichallenges2025may.scrollablestudyboard

data class ScrollableStudyBoardState(
    val lessonTopicList: List<LessonTopic> = lessonTopics
)

fun List<LessonTopic>.groupByCategory() = this.groupBy { it.category }
fun List<LessonTopic>.groupByCategoryMapKey() = this.groupBy { it.category }.map { it.key }
fun List<LessonTopic>.filterPinList() = this.filter { it.isPinned }
fun List<LessonTopic>.filterNotPinList() = this.filter { !it.isPinned }
fun List<LessonTopic>.getHeaderIndexHashForLazyColumn(): HashMap<String, Int> {
    val headerHashMap = hashMapOf<String, Int>()
    var currentIndex = 0

    this.groupByCategory().forEach { (category, lessons) ->
        headerHashMap[category] = currentIndex
        currentIndex += 1 + lessons.filterNot { it.isPinned }.size
    }
    return headerHashMap
}