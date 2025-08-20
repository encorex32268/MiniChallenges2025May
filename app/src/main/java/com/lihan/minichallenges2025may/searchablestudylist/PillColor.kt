package com.lihan.minichallenges2025may.searchablestudylist


import androidx.compose.ui.graphics.Color
import com.lihan.minichallenges2025may.ui.theme.GreenPillBG
import com.lihan.minichallenges2025may.ui.theme.GreenPillTextColor
import com.lihan.minichallenges2025may.ui.theme.OrangePillBG
import com.lihan.minichallenges2025may.ui.theme.OrangePillTextColor
import com.lihan.minichallenges2025may.ui.theme.PinkPillBG
import com.lihan.minichallenges2025may.ui.theme.PinkPillTextColor
import com.lihan.minichallenges2025may.ui.theme.Primary
import com.lihan.minichallenges2025may.ui.theme.PurplePillColor


data class PillColor(
    val text: Color,
    val background: Color
){
    companion object{
        fun getColor(text: String): PillColor {
            return when(text){
                //Green
                in listOf("History","Biology","Earth Science") -> PillColor(text = GreenPillTextColor, background = GreenPillBG)
                //Pink
                in listOf("Math","Literature","Spanish") -> PillColor(text = PinkPillTextColor, background = PinkPillBG)
                //Orange
                in listOf("Geography","Language","Social Studies") -> PillColor(text = OrangePillTextColor, background = OrangePillBG)
                //Primary
                else -> PillColor(text = Primary, background = PurplePillColor)
            }
        }

    }
}
