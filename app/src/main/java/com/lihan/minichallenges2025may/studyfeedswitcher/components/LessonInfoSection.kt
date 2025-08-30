package com.lihan.minichallenges2025may.studyfeedswitcher.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.minichallenges2025may.studyfeedswitcher.PillBG
import com.lihan.minichallenges2025may.studyfeedswitcher.PrimaryText
import com.lihan.minichallenges2025may.studyfeedswitcher.QuickLessonUi
import com.lihan.minichallenges2025may.studyfeedswitcher.SecondaryText
import com.lihan.minichallenges2025may.studyfeedswitcher.quickLessonUiList
import com.lihan.minichallenges2025may.ui.montserrat
import com.lihan.minichallenges2025may.ui.poltawskinowy
import com.lihan.minichallenges2025may.ui.theme.MiniChallenges2025MayTheme

@Composable
fun LessonInfoSection(
    modifier: Modifier = Modifier,
    quickLessonUi: QuickLessonUi
) {
    Column(modifier = modifier){
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(color = PillBG)
                .padding(vertical = 2.dp, horizontal = 12.dp)
            ,
            text = quickLessonUi.subject,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = montserrat,
                color = PrimaryText,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = quickLessonUi.title,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = PrimaryText,
                fontWeight = FontWeight.Bold,
                fontFamily = poltawskinowy,
                fontSize = 38.sp
            ),
            lineHeight = 40.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = quickLessonUi.description,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = montserrat,
                color = SecondaryText,
                fontWeight = FontWeight.Normal,
                fontSize = 19.sp
            )
        )

    }



}

@Composable
@Preview
private fun LessonInfoSectionPreview(){
    MiniChallenges2025MayTheme {
        LessonInfoSection(
            quickLessonUi = quickLessonUiList[5]
        )
    }
}