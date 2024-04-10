package com.example.mobile_laboratoryproject2.view.video_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mobile_laboratoryproject2.view.common.NavBar
import com.example.mobile_laboratoryproject2.viewModel.navigation.Destination

// Экран перехода на сайт
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VideoScreen(
    onTrainingClick: () -> Unit,
    onDictionaryClick: () -> Unit
) {

    Scaffold(
        bottomBar = {
            NavBar(
                currentScreen = Destination.VideoScreen,
                onTrainingClick = onTrainingClick,
                onDictionaryClick = onDictionaryClick
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Text(text = "video screen")
        }
    }
}