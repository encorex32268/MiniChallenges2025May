package com.lihan.minichallenges2025may.studyfeedswitcher

// Quick Lessons Reference:
// 1. “Inside a Cell” • A 60‑second tour of organelles • Biology • Leaf‑green gradient
// 2. “Fractions in a Flash” • Understand halves, thirds, and quarters fast • Math • Indigo gradient
// 3. “WWII at a Glance” • Key events and dates distilled into one minute • History • Amber gradient
// 4. “Bonjour Basics” • Greet and introduce yourself in French • Language • Denim‑blue gradient
// 5. “Sketch Like Picasso” • Quick warm‑up for abstract line art • Art • Coral gradient
// 6. “Binary in 60 Seconds” • Count to 32 on one hand using binary • Computer Science • Teal gradient
// 7. “Chord Progression 101” • The I‑V‑vi‑IV pattern explained • Music • Plum gradient
// 8. “Mountains & Valleys” • How tectonic plates shape landscapes • Geography • Earth‑brown gradient

data class QuickLesson(
    val title: String,
    val description: String,
    val subject: String,
    val gradient: String
)

val quickLessons = listOf(
    QuickLesson("Inside a Cell", "A 60‑second tour of organelles", "Biology", "Leaf‑green gradient"),
    QuickLesson("Fractions in a Flash", "Understand halves, thirds, and quarters fast", "Math", "Indigo gradient"),
    QuickLesson("WWII at a Glance", "Key events and dates distilled into one minute", "History", "Amber gradient"),
    QuickLesson("Bonjour Basics", "Greet and introduce yourself in French", "Language", "Denim‑blue gradient"),
    QuickLesson("Sketch Like Picasso", "Quick warm‑up for abstract line art", "Art", "Coral gradient"),
    QuickLesson("Binary in 60 Seconds", "Count to 32 on one hand using binary", "Computer Science", "Teal gradient"),
    QuickLesson("Chord Progression 101", "The I‑V‑vi‑IV pattern explained", "Music", "Plum gradient"),
    QuickLesson("Mountains & Valleys", "How tectonic plates shape landscapes", "Geography", "Earth‑brown gradient")
)

