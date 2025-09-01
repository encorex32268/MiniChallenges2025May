@file:OptIn(ExperimentalFoundationApi::class)

package com.lihan.minichallenges2025may.scrollablestudyboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.minichallenges2025may.R
import com.lihan.minichallenges2025may.ui.montserrat
import com.lihan.minichallenges2025may.ui.theme.MiniChallenges2025MayTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

val Primary = Color(0xFF6B74F8)
val Background = Color(0xFFEDEDFC)
val Surface = Color.White
val HigherSurface = Color(0xFFEFEFFC)
val PrimaryText = Color(0xFF13182C)
val SecondaryText = Color(0xFF4C4F59)
val Tertiary = Color(0xFF9296D1)
val Icon = Color(0xFF9FA3AF)

@Composable
fun ScrollableStudyBoard(
    modifier: Modifier = Modifier,
    state: ScrollableStudyBoardState,
    onAction: (ScrollableStudyBoardAction) -> Unit
) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val itemHeight = remember { mutableFloatStateOf(0f) }
    val progress = remember { derivedStateOf {
        val totalItemsCount = listState.layoutInfo.totalItemsCount
        val index = listState.firstVisibleItemIndex
        val offset  = listState.firstVisibleItemScrollOffset
        if (itemHeight.floatValue == 0f || totalItemsCount == 0) {
            return@derivedStateOf 0f
        }
        val viewportHeight = listState.layoutInfo.viewportSize.height
        val totalContentHeight = totalItemsCount * itemHeight.floatValue
        val totalScrollablePx = (totalContentHeight - viewportHeight)
        val currentScrollPx = (index * itemHeight.floatValue) + offset
        if (totalScrollablePx > 0) {
            (currentScrollPx / totalScrollablePx).coerceIn(0f, 1f)
        } else {
            0f
        }
    } }

    var isShowFabButton by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(listState) {
        // 將 listState 的 firstVisibleItemIndex 轉換成 Flow
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .onEach { firstIndex ->
                isShowFabButton = firstIndex >= 10
            }
            .launchIn(this)
    }

    Scaffold(
        floatingActionButton = {
            if (isShowFabButton){
                FloatingActionButton(
                    modifier = Modifier.size(80.dp),
                    containerColor = HigherSurface,
                    shape = CircleShape,
                    onClick = {
                        scope.launch {
                            listState.animateScrollToItem(0)
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier.padding(16.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.arrow_up_fab),
                        contentDescription = "ArrowUpFab",
                        tint = Color.Unspecified
                    )
                }
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Background)
                .padding(it)
        ){
            LazyRow(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                itemsIndexed(state.lessonTopicList.groupByCategoryMapKey()){ index,topicChip  ->
                    TopicChip(text = topicChip)
                }
            }
            ScrollProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(1.dp),
                progress = { progress.value }
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(top = 20.dp),
                state = listState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(20.dp)
            ){
                items(state.lessonTopicList.filterPinList()){ lessonTopic ->
                    TopicsCard(
                        lessonTopic = lessonTopic,
                        onPinClick = { pinItem ->
                            onAction(ScrollableStudyBoardAction.OnPinItem(pinItem))
                        },
                        onItemClick = {
                            println("TopicsCard Pined ${lessonTopic}")
                            onAction(ScrollableStudyBoardAction.ToDetail(lessonTopic))
                        }
                    )
                }
                state.lessonTopicList.groupByCategory().forEach { key , value ->
                    val notPinedList =  value.filterNotPinList()

                    stickyHeader{
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFEDEDFC).copy(alpha = 0.9f)),
                            contentAlignment = Alignment.BottomStart
                        ){
                            Text(
                                text = "$key",
                                color = Tertiary,
                                fontFamily = montserrat,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp,
                                lineHeight = 24.sp
                            )
                        }
                    }
                    items(notPinedList){ lessonTopic ->
                        TopicsCard(
                            modifier = Modifier.onGloballyPositioned{ coordinates ->
                                if (itemHeight.floatValue == 0f){
                                    itemHeight.floatValue = coordinates.size.height.toFloat()
                                }
                            },
                            lessonTopic = lessonTopic,
                            onPinClick = { pinItem ->
                                onAction(ScrollableStudyBoardAction.OnPinItem(pinItem))
                            },
                            onItemClick = {
                                println("TopicsCard  ${lessonTopic}")
                                onAction(ScrollableStudyBoardAction.ToDetail(lessonTopic))
                            }
                        )
                    }
                }
            }

        }

    }



}

@Composable
fun ScrollProgressBar(
    modifier: Modifier = Modifier,
    progress: () -> Float,
) {
    Canvas(modifier = modifier) {
        val width = size.width
        drawLine(
            strokeWidth = 10f,
            color = Primary,
            start = Offset(0f,0f),
            end = Offset(
                x = width * progress(),
                y = 0f
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ScrollableStudyBoardPreview() {
    MiniChallenges2025MayTheme {
        ScrollableStudyBoard(
            state = ScrollableStudyBoardState(
                lessonTopics
            ),
            onAction = {}
        )
    }

}