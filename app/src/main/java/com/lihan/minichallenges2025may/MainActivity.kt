package com.lihan.minichallenges2025may

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lihan.minichallenges2025may.searchablestudylist.SearchableStudyList
import com.lihan.minichallenges2025may.searchablestudylist.StudyViewModel
import com.lihan.minichallenges2025may.ui.theme.MiniChallenges2025MayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniChallenges2025MayTheme {
                val viewModel by viewModels<StudyViewModel>()
                val studyState by viewModel.state.collectAsStateWithLifecycle()

                Scaffold(modifier = Modifier.fillMaxSize().background(color = Color.White),) { innerPadding ->
                    SearchableStudyList(
                        modifier = Modifier.padding(innerPadding),
                        state = studyState,
                        onAction = {
                            viewModel.onAction(it)
                        }
                    )
                }
            }
        }
    }
}
