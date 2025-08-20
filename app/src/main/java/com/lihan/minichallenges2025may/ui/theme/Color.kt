package com.lihan.minichallenges2025may.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)



val ColorScheme.BgGradient: Brush
    get() = Brush.linearGradient(
        colors = listOf(
            Color(0xFF6B74F8).copy(alpha = 0.15f),
            Color(0xFFFDE5F3).copy(alpha = 0.15f),
            Color(0xFFFEF7EE).copy(alpha = 0.15f)
        )
    )

val ColorScheme.Surface: Color
    get() = Color.White

val ColorScheme.HigherSurface: Color
    get() = Color(0xFFF5F5F8)

val ColorScheme.PrimaryText: Color
    get() = Color(0xFF13182C)

val ColorScheme.TertiaryText: Color
    get() = Color(0xFF9296D1)

val ColorScheme.Icon: Color
    get() = Color(0xFF4C4F59)

val Primary = Color(0xFF6B74F8)
val PurplePillColor = Color(0xFFEFEFFC)

val GreenPillTextColor = Color(0xFF03A564)
val GreenPillBG = Color(0xFFE5FBF2)

val PinkPillTextColor = Color(0xFFDC3C9A)
val PinkPillBG = Color(0xFFFDE5F3)

val OrangePillTextColor = Color(0xFFF78018)
val OrangePillBG = Color(0xFFFCF5EF)

