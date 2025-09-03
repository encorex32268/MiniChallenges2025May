package com.lihan.minichallenges2025may.scrollablestudyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.minichallenges2025may.ui.montserrat
import com.lihan.minichallenges2025may.ui.theme.MiniChallenges2025MayTheme

@Composable
fun TopicChip(
    text: String,
    onChipClick: () -> Unit = {},
    fontSize: TextUnit = 17.sp,
    color: Color = PrimaryText,
    backgroundColor: Color = Surface,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .clickable{
                onChipClick()
            }
            .background(color = backgroundColor)
            .padding(vertical = 5.dp, horizontal = 12.dp)
        ,
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            fontSize = fontSize,
            color = color,
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium
        )
    }

}

@Preview
@Composable
private fun TopicChipPreview() {
    MiniChallenges2025MayTheme {
        TopicChip(text = "Math" , onChipClick = {})
    }

}