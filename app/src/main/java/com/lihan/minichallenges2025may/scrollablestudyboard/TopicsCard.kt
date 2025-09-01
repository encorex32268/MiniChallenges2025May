package com.lihan.minichallenges2025may.scrollablestudyboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.minichallenges2025may.R
import com.lihan.minichallenges2025may.ui.poltawskinowy
import com.lihan.minichallenges2025may.ui.theme.MiniChallenges2025MayTheme

@Composable
fun TopicsCard(
    lessonTopic: LessonTopic,
    onPinClick: (LessonTopic) -> Unit,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconBackgroundColor = remember(lessonTopic){
        if (lessonTopic.isPinned){
            Primary.copy(alpha = 0.15f)
        }else{
            Color.Unspecified
        }
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Surface
        ),
        border = if (lessonTopic.isPinned){
            BorderStroke(1.dp,Primary)
        }else{
            null
        }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{
                    onItemClick()
                }
                .padding(horizontal = 16.dp)

            ,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .weight(1f),
                text = lessonTopic.title,
                fontSize = 22.sp,
                fontFamily = poltawskinowy,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp
            )
            Box(
                modifier = Modifier
                    .padding(vertical = 14.dp)
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(iconBackgroundColor)
                    .clickable{
                        onPinClick(lessonTopic)
                    }

                ,
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = if (lessonTopic.isPinned){
                        ImageVector.vectorResource(R.drawable.pin_fill)
                    }else{
                        ImageVector.vectorResource(R.drawable.pin_outline)
                    },
                    contentDescription = "Pin Icon",
                    tint = Color.Unspecified
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun TopicsCardPreview() {
    MiniChallenges2025MayTheme {
        var isPinned by remember {
            mutableStateOf(false)
        }
        TopicsCard(
            lessonTopic = LessonTopic("Photosynthesis Basics", "Science", isPinned = isPinned),
            onPinClick = {
                isPinned = !isPinned
            },
            onItemClick = {

            }
        )
    }

}