package com.lihan.minichallenges2025may.studyfeedswitcher

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.minichallenges2025may.R
import com.lihan.minichallenges2025may.studyfeedswitcher.components.LessonInfoSection
import com.lihan.minichallenges2025may.ui.montserrat
import com.lihan.minichallenges2025may.ui.theme.MiniChallenges2025MayTheme
import kotlinx.coroutines.launch

//Study Feed Switcher
val PrimaryText = Color.White
val SecondaryText =  Color.White.copy(alpha = 0.9f)
val Icon = Color.White.copy(alpha = 0.6f)
val PillBG = Color.White.copy(alpha = 0.2f)


@Composable
fun StudyFeedSwitcher(
    state: StudyFeedSwitcherState,
    onAction: (StudyFeedSwitcherAction) -> Unit,
    modifier: Modifier = Modifier,
    isLandscape: Boolean = false
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { state.quickLessonUis.size }
    )
    LaunchedEffect(pagerState.isScrollInProgress) {
        if (pagerState.isScrollInProgress){
            onAction(StudyFeedSwitcherAction.OnScrolling)
        }else{
            onAction(StudyFeedSwitcherAction.OnStopScrolling)
        }
    }
    LaunchedEffect(pagerState.currentPage) {
        when (pagerState.currentPage) {
            0 -> {
                pagerState.scrollToPage(state.quickLessonUis.size -2)
            }
            state.quickLessonUis.size - 1 -> {
                pagerState.scrollToPage(1)
            }
            else -> Unit
        }
    }
    Log.d("TAG", "StudyFeedSwitcher: ${pagerState.targetPage}")

    if (isLandscape){
        HorizontalPager(
            state = pagerState,
            modifier = modifier.fillMaxSize()
        ) { page ->
            val quickLesson =  state.quickLessonUis[page]
            StudyFeedSwitcherContent(
                state = state,
                quickLessonUi = quickLesson,
                onPreviousPageClick = {
                    scope.launch {
                        onAction(StudyFeedSwitcherAction.OnScrolling)
                        pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                    }
                },
                onNextPageClick = {
                    scope.launch {
                        onAction(StudyFeedSwitcherAction.OnScrolling)
                        pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                    }
                },
                isLandscape = true
            )
        }
    }else{
        VerticalPager(
            state = pagerState,
            modifier = modifier.fillMaxSize()
        ) { page ->
            val quickLesson =  state.quickLessonUis[page]
            StudyFeedSwitcherContent(
                state = state,
                quickLessonUi = quickLesson,
                onPreviousPageClick = {
                    scope.launch {
                        onAction(StudyFeedSwitcherAction.OnScrolling)
                        pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                    }
                },
                onNextPageClick = {
                    scope.launch {
                        onAction(StudyFeedSwitcherAction.OnScrolling)
                        pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                    }
                },
                isLandscape = false
            )
        }

    }




}


@Composable
private fun StudyFeedSwitcherContent(
    modifier: Modifier = Modifier,
    state: StudyFeedSwitcherState,
    quickLessonUi: QuickLessonUi,
    onPreviousPageClick: () -> Unit,
    onNextPageClick: () -> Unit,
    isLandscape: Boolean
){
    if (isLandscape){
        Row(
            modifier = modifier
                .fillMaxSize()
                .background(brush = quickLessonUi.getBackgroundBrush())
                .padding(vertical = 32.dp, horizontal = 56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(
                onClick = onPreviousPageClick
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_left),
                    contentDescription = "PreviousIcon",
                    tint = Icon
                )
            }
            StudyFeedSwitcherBody(
                quickLessonUi = quickLessonUi,
                isShowSwipeHighlights = state.isShowSwipeHighlights,
                modifier = Modifier.fillMaxHeight()
                    .weight(1f)
                    .padding(horizontal = 83.dp)
                    .padding(top = 8.dp , bottom = 32.dp),
            )
            IconButton(
                onClick = onNextPageClick
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_right),
                    contentDescription = "NextIcon",
                    tint = Icon
                )
            }
        }
    }else{
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(brush = quickLessonUi.getBackgroundBrush())
                .padding(vertical = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            IconButton(
                onClick = onPreviousPageClick
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_up),
                    contentDescription = "PreviousIcon",
                    tint = Icon
                )
            }
            StudyFeedSwitcherBody(
                quickLessonUi = quickLessonUi,
                isShowSwipeHighlights = state.isShowSwipeHighlights,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 32.dp)
                    .padding(top = 8.dp , bottom = 32.dp),
            )
            IconButton(
                onClick = onNextPageClick
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_down),
                    contentDescription = "NextIcon",
                    tint = Icon
                )
            }
        }
    }

}

@Composable
fun StudyFeedSwitcherBody(
    modifier: Modifier = Modifier,
    quickLessonUi: QuickLessonUi,
    isShowSwipeHighlights: Boolean,
){
    Column(
        modifier = modifier
    ){
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isShowSwipeHighlights,
            enter = slideInHorizontally() + fadeIn(animationSpec = tween(durationMillis = 300)),
            exit = slideOutHorizontally() + fadeOut(animationSpec = tween(durationMillis = 300))
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text ="Swipe to see more",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = PrimaryText,
                    fontFamily = montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 19.sp,
                    textAlign = TextAlign.Center
                ),
                lineHeight = 24.sp
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.BottomStart
        ){
            LessonInfoSection(
                quickLessonUi = quickLessonUi
            )
        }
    }

}


@Composable
@Preview(showSystemUi = true, device = "id:pixel_5")
private fun StudyFeedSwitcherPreview() {
    MiniChallenges2025MayTheme {
        StudyFeedSwitcher(
            state = StudyFeedSwitcherState(
                isShowSwipeHighlights = false
            ),
            onAction = {}
        )
    }
}

@Composable
@Preview(showSystemUi = true, device = "spec:parent=pixel_5,orientation=landscape")
private fun StudyFeedSwitcherLandscapePreview() {
    MiniChallenges2025MayTheme {
        StudyFeedSwitcher(
            state = StudyFeedSwitcherState(
                isShowSwipeHighlights = true
            ),
            onAction = {},
            isLandscape = true
        )
    }
}


/*
 Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = quickLessonUi.getBackgroundBrush())
                .padding(vertical = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

        }
 */