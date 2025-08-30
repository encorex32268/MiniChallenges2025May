package com.lihan.minichallenges2025may.studyfeedswitcher

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


private val GrassGreenGradient = Brush.verticalGradient(colors = listOf(Color(0xFFAFD27A),Color(0xFF287F3C)))
private val BlueGradient = Brush.verticalGradient(colors = listOf(Color(0xFF1288FF),Color(0xFF0b2299)))
private val OrangeGradient = Brush.verticalGradient(colors = listOf(Color(0xFFF5C020),Color(0xFFCF5B08)))
private val JeanBlueGradient = Brush.verticalGradient(colors = listOf(Color(0xFF41A5CC),Color(0xFF055270)))
private val CoralGradient = Brush.verticalGradient(colors = listOf(Color(0xFFFF9D89),Color(0xFFD03515)))
private val TealGradient = Brush.verticalGradient(colors = listOf(Color(0xFF41DDBE),Color(0xFF087578)))
private val PlumGradient = Brush.verticalGradient(colors = listOf(Color(0xFF9E77AC),Color(0xFF451C52)))
private val EarthyBrownGradient = Brush.verticalGradient(colors = listOf(Color(0xFF986241),Color(0xFF402302)))

data class QuickLessonUi(
    val title: String,
    val description: String,
    val subject: String,
    val gradient: String
)

fun QuickLesson.toQuickLessonUi(): QuickLessonUi{
    return QuickLessonUi(title,description,subject,gradient)
}

val quickLessonUiList = buildList {
    val mappedList = quickLessons.map { it.toQuickLessonUi() }
    add(mappedList.last())
    addAll(mappedList)
    add(mappedList.first())
}

fun QuickLessonUi.getBackgroundBrush(): Brush {
    return when(gradient){
        "Leaf‑green gradient" -> GrassGreenGradient
        "Indigo gradient" -> BlueGradient
        "Amber gradient" -> OrangeGradient
        "Denim‑blue gradient" -> JeanBlueGradient
        "Coral gradient" -> CoralGradient
        "Teal gradient" -> TealGradient
        "Plum gradient" -> PlumGradient
        "Earth‑brown gradient" -> EarthyBrownGradient

        else -> GrassGreenGradient
    }
}


