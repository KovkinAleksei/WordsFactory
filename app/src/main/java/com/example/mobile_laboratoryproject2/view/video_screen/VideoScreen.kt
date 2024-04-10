package com.example.mobile_laboratoryproject2.view.video_screen

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.mobile_laboratoryproject2.view.common.NavBar
import com.example.mobile_laboratoryproject2.viewModel.navigation.Destination
import com.example.mobile_laboratoryproject2.viewModel.video_screen.VideoViewModel
import org.koin.androidx.compose.koinViewModel

// Экран перехода на сайт
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VideoScreen(
    onTrainingClick: () -> Unit,
    onDictionaryClick: () -> Unit,
    viewModel: VideoViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            NavBar(
                currentScreen = Destination.VideoScreen,
                onTrainingClick = onTrainingClick,
                onDictionaryClick = onDictionaryClick
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            AndroidView(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 98.dp),
                factory = { context ->
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        webViewClient = uiState.viewClient

                        settings.loadWithOverviewMode = true
                        //settings.useWideViewPort = true
                    }
                },
                update = { webView ->
                    webView.loadUrl(uiState.url)
                }
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(0.dp, 0.dp, 0.dp, 98.dp)
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.White)
                        )
                    )
            )
        }
    }
}