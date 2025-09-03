package com.lihan.minichallenges2025may

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.lihan.minichallenges2025may.scrollablestudyboard.CourseDetail
import com.lihan.minichallenges2025may.scrollablestudyboard.LessonTopic
import com.lihan.minichallenges2025may.scrollablestudyboard.ScrollableStudyBoard
import com.lihan.minichallenges2025may.scrollablestudyboard.ScrollableStudyBoardAction
import com.lihan.minichallenges2025may.scrollablestudyboard.ScrollableStudyBoardViewModel
import com.lihan.minichallenges2025may.searchablestudylist.SearchableStudyList
import com.lihan.minichallenges2025may.searchablestudylist.StudyViewModel
import com.lihan.minichallenges2025may.studyfeedswitcher.StudyFeedSwitcher
import com.lihan.minichallenges2025may.studyfeedswitcher.StudyFeedSwitcherViewModel
import com.lihan.minichallenges2025may.ui.theme.MiniChallenges2025MayTheme
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
data object StudyBoard

@Serializable
data class StudyBoardDetail(val lessonTopicString: String)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniChallenges2025MayTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = StudyBoard
                ){
                    composable<StudyBoard>{
                        val viewModel by viewModels<ScrollableStudyBoardViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        ScrollableStudyBoard(
                            state = state,
                            onAction = { action ->
                                when(action){
                                    is ScrollableStudyBoardAction.ToDetail -> {
                                        val lessonTopicString = Json.encodeToString(action.lessonTopic)
                                        navController.navigate(StudyBoardDetail(lessonTopicString = lessonTopicString))
                                    }
                                    else -> Unit
                                }
                                viewModel.onAction(action)
                            },
                            uiEvent = viewModel.uiEvent

                        )

                    }
                    composable<StudyBoardDetail>{
                        val lessonTopicString = it.toRoute<StudyBoardDetail>().lessonTopicString
                        val lessonTopic = Json.decodeFromString<LessonTopic>(lessonTopicString)
                        CourseDetail(
                            title = lessonTopic.title,
                            category = lessonTopic.category,
                            onBackClick = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}
