@file:OptIn(ExperimentalLayoutApi::class)

package com.lihan.minichallenges2025may.searchablestudylist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.minichallenges2025may.searchablestudylist.PillColor
import com.lihan.minichallenges2025may.ui.montserrat
import com.lihan.minichallenges2025may.ui.poltawskinowy
import com.lihan.minichallenges2025may.ui.theme.MiniChallenges2025MayTheme
import com.lihan.minichallenges2025may.ui.theme.PrimaryText

@Composable
fun TopicCard(
    title: String,
    topics: List<String>,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    modifier: Modifier = Modifier
) {
    val shadowColor = MaterialTheme.colorScheme.PrimaryText.copy(alpha = 0.03f)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = shape)
            .shadow(
                elevation = 8f.dp,
                spotColor = shadowColor,
                shape = shape
            )
            .padding(16.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){
            topics.forEach { topic ->
                TopicChip(topic)
            }
        }
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.PrimaryText,
                fontSize = 22.sp,
                fontFamily = poltawskinowy,

            )
        )
    }
}


@Composable
private fun TopicChip(
    topic: String,
    modifier: Modifier = Modifier
){
    val pillColor = remember(topic){
        PillColor.getColor(topic)
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = pillColor.background),
        contentAlignment = Alignment.Center
    ){
        Text(
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 12.dp),
            text = topic,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = montserrat,
                color = pillColor.text,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun TopicCardPreview(){
    MiniChallenges2025MayTheme {
        Box(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            contentAlignment = Alignment.Center
        ){
            TopicCard(
                title = "Title",
                topics = emptyList()
            )
        }
    }
}