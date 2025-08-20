package com.lihan.minichallenges2025may.searchablestudylist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.minichallenges2025may.searchablestudylist.components.StudySearchBar
import com.lihan.minichallenges2025may.searchablestudylist.components.TopicCard
import com.lihan.minichallenges2025may.ui.montserrat
import com.lihan.minichallenges2025may.ui.theme.BgGradient
import com.lihan.minichallenges2025may.ui.theme.MiniChallenges2025MayTheme
import com.lihan.minichallenges2025may.ui.theme.PrimaryText
import com.lihan.minichallenges2025may.ui.theme.TertiaryText


@Composable
fun SearchableStudyList(
    modifier: Modifier = Modifier,
    state: StudyState,
    onAction: (StudyAction) -> Unit
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier.padding(vertical = 12.dp),
            text =  "Study Topics",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.PrimaryText,
                fontFamily = montserrat,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 32.sp,
                fontSize = 22.sp
            )
        )
        StudySearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            text = state.searchText,
            onValueChange = {
                onAction(StudyAction.OnSearchTextChange(it))
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
           Box(
               modifier = Modifier
                   .fillMaxSize()
                   .background(
                       brush = MaterialTheme.colorScheme.BgGradient,
                   )
                   .padding(20.dp)
           ){
               when{
                   state.isLoading -> {}
                   state.items.isEmpty() -> {
                       Text(
                           modifier = Modifier.fillMaxWidth(),
                           text = "No results found, try searching again! ",
                           style = MaterialTheme.typography.bodyMedium.copy(
                               fontWeight = FontWeight.SemiBold,
                               fontSize = 15.sp,
                               textAlign = TextAlign.Center,
                               lineHeight = 24.sp,
                               color = MaterialTheme.colorScheme.TertiaryText
                           )
                       )
                   }
                   else -> {
                       LazyColumn(
                           modifier = Modifier.fillMaxSize(),
                           verticalArrangement = Arrangement.spacedBy(8.dp)
                       ){
                           items(state.items){ studyTopic ->
                               TopicCard(
                                   title = studyTopic.title,
                                   topics = studyTopic.subjects
                               )
                           }
                       }
                   }

               }

           }

        }

    }

}


@Composable
@Preview(showBackground = true)
private fun SearchableStudyListPreview(){
    MiniChallenges2025MayTheme {
        SearchableStudyList(
            state = StudyState(
                items = studyTopics
            ),
            onAction = {}
        )
    }
}