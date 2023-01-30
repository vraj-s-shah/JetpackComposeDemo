package com.example.onlinelearning.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.onlinelearning.utils.startAndFinish
import com.example.onlinelearning.viewmodel.SplashScreenViewModel
import com.example.onlinelearning.viewmodel.obtainViewModel

class MainActivity : BaseComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val splashScreenViewModel = obtainViewModel<SplashScreenViewModel>()
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                SplashScreen(splashScreenViewModel) { activity ->
                    startAndFinish(activity)
                }
            }
        }
        hideStatusBar()
    }
}