package com.lihan.minichallenges2025may.scrollablestudyboard

import kotlinx.serialization.Serializable

// Master Lesson Topics Reference:
//
// Science
// 1. Photosynthesis Basics
// 2. Newton’s Laws of Motion
// 3. The Water Cycle
// 4. Types of Energy
// 5. Ecosystems and Biomes
// 6. Cell Structure and Function
// 7. States of Matter
// 8. Gravity and Forces
// 9. The Human Body Systems
// 10. Food Chains and Webs
//
// Math
// 11. Solving for X
// 12. Introduction to Fractions
// 13. Understanding Decimals
// 14. Area and Perimeter
// 15. Mean, Median, and Mode
// 16. Multiplication Strategies
// 17. Long Division Basics
// 18. Intro to Algebraic Expressions
// 19. Coordinate Grids
// 20. Percentages and Ratios
//
// Language
// 21. Basic Spanish Greetings
// 22. Common French Verbs
// 23. Parts of Speech
// 24. Verb Tenses Explained
// 25. Active vs Passive Voice
// 26. Building Vocabulary
// 27. Prefixes and Suffixes
// 28. Capitalization Rules
// 29. Sentence Types
// 30. Transition Words
//
// History
// 31. Ancient Egypt
// 32. The Roman Empire
// 33. The Middle Ages
// 34. World War I Overview
// 35. World War II Key Events
// 36. The Cold War
// 37. Civil Rights Movement
// 38. American Revolution
// 39. Industrial Revolution
// 40. Timeline of Key Inventions
//
// Literature
// 41. Elements of a Story
// 42. Literary Devices
// 43. Alliteration and Assonance
// 44. Understanding Plot Structure
// 45. Theme and Tone
// 46. Conflict in Literature
// 47. Point of View in Writing
// 48. Character Development
// 49. Poetry Forms and Structures
// 50. Figurative Language
//
// Geography
// 51. Continents and Oceans
// 52. Latitude and Longitude
// 53. World Time Zones
// 54. Landforms and Bodies of Water
// 55. Natural Disasters
// 56. Map Reading Skills
// 57. Climate Zones
// 58. Countries and Capitals
// 59. Population Density
// 60. Regions of the World

@Serializable
data class LessonTopic(
    val title: String,
    val category: String,
    val isPinned: Boolean = false
)



val lessonTopics = listOf(
    // Science
    LessonTopic("Photosynthesis Basics", "Science"),
    LessonTopic("Newton’s Laws of Motion", "Science"),
    LessonTopic("The Water Cycle", "Science"),
    LessonTopic("Types of Energy", "Science"),
    LessonTopic("Ecosystems and Biomes", "Science"),
    LessonTopic("Cell Structure and Function", "Science"),
    LessonTopic("States of Matter", "Science"),
    LessonTopic("Gravity and Forces", "Science"),
    LessonTopic("The Human Body Systems", "Science"),
    LessonTopic("Food Chains and Webs", "Science"),

    // Math
    LessonTopic("Solving for X", "Math"),
    LessonTopic("Introduction to Fractions", "Math"),
    LessonTopic("Understanding Decimals", "Math"),
    LessonTopic("Area and Perimeter", "Math"),
    LessonTopic("Mean, Median, and Mode", "Math"),
    LessonTopic("Multiplication Strategies", "Math"),
    LessonTopic("Long Division Basics", "Math"),
    LessonTopic("Intro to Algebraic Expressions", "Math"),
    LessonTopic("Coordinate Grids", "Math"),
    LessonTopic("Percentages and Ratios", "Math"),

    // Language
    LessonTopic("Basic Spanish Greetings", "Language"),
    LessonTopic("Common French Verbs", "Language"),
    LessonTopic("Parts of Speech", "Language"),
    LessonTopic("Verb Tenses Explained", "Language"),
    LessonTopic("Active vs Passive Voice", "Language"),
    LessonTopic("Building Vocabulary", "Language"),
    LessonTopic("Prefixes and Suffixes", "Language"),
    LessonTopic("Capitalization Rules", "Language"),
    LessonTopic("Sentence Types", "Language"),
    LessonTopic("Transition Words", "Language"),

    // History
    LessonTopic("Ancient Egypt", "History"),
    LessonTopic("The Roman Empire", "History"),
    LessonTopic("The Middle Ages", "History"),
    LessonTopic("World War I Overview", "History"),
    LessonTopic("World War II Key Events", "History"),
    LessonTopic("The Cold War", "History"),
    LessonTopic("Civil Rights Movement", "History"),
    LessonTopic("American Revolution", "History"),
    LessonTopic("Industrial Revolution", "History"),
    LessonTopic("Timeline of Key Inventions", "History"),

    // Literature
    LessonTopic("Elements of a Story", "Literature"),
    LessonTopic("Literary Devices", "Literature"),
    LessonTopic("Alliteration and Assonance", "Literature"),
    LessonTopic("Understanding Plot Structure", "Literature"),
    LessonTopic("Theme and Tone", "Literature"),
    LessonTopic("Conflict in Literature", "Literature"),
    LessonTopic("Point of View in Writing", "Literature"),
    LessonTopic("Character Development", "Literature"),
    LessonTopic("Poetry Forms and Structures", "Literature"),
    LessonTopic("Figurative Language", "Literature"),

    // Geography
    LessonTopic("Continents and Oceans", "Geography"),
    LessonTopic("Latitude and Longitude", "Geography"),
    LessonTopic("World Time Zones", "Geography"),
    LessonTopic("Landforms and Bodies of Water", "Geography"),
    LessonTopic("Natural Disasters", "Geography"),
    LessonTopic("Map Reading Skills", "Geography"),
    LessonTopic("Climate Zones", "Geography"),
    LessonTopic("Countries and Capitals", "Geography"),
    LessonTopic("Population Density", "Geography"),
    LessonTopic("Regions of the World", "Geography")
)