@file:OptIn(ExperimentalMaterial3Api::class)

package com.lihan.minichallenges2025may.scrollablestudyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.minichallenges2025may.R
import com.lihan.minichallenges2025may.searchablestudylist.PillColor
import com.lihan.minichallenges2025may.ui.montserrat
import com.lihan.minichallenges2025may.ui.poltawskinowy
import com.lihan.minichallenges2025may.ui.theme.MiniChallenges2025MayTheme

@Composable
fun CourseDetail(
    title: String,
    category: String,
    onBackClick: () -> Unit
) {
    val categoryPillColor = remember(category) {
        PillColor.getColor(category)
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Primary,
                    navigationIconContentColor = Surface,
                    titleContentColor = Surface
                ),
                title = {
                    Text(
                        text = "Course Details",
                        fontFamily = montserrat,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        lineHeight = 24.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.arrow_left_back),
                            contentDescription = "ArrowLeftBack"
                        )
                    }
                }
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Surface)
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = title,
                fontFamily = poltawskinowy,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                lineHeight = 36.sp
            )
            TopicChip(
                text = category,
                color = categoryPillColor.text,
                backgroundColor = categoryPillColor.background,
                fontSize = 15.sp
            )
        }
    }

}


@Preview
@Composable
private fun CourseDetailPreview() {
    MiniChallenges2025MayTheme {
        val lessonTopic = lessonTopics.first()
        CourseDetail(
            title = lessonTopic.title,
            category = lessonTopic.category,
            onBackClick = {}
        )
    }

}