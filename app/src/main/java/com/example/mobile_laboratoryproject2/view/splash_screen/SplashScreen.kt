package com.example.mobile_laboratoryproject2.view.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.ui.theme.DarkColor
import com.example.mobile_laboratoryproject2.viewModel.splash_screen.SplashScreenViewModel
import org.koin.androidx.compose.koinViewModel

// Экран запуска приложения
@Composable
fun SplashScreen(
    navigateToOnBoarding: () -> Unit,
    navigateTosignIn: () -> Unit,
    viewModel: SplashScreenViewModel = koinViewModel()
) {
    // Переход к следующему экрану приложения
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(null) {
        viewModel.uiState.collect {
            if (it.isCompleted) {
                if (it.isOnBoardingShown)
                    navigateTosignIn()
                else
                    navigateToOnBoarding()
            }
        }
    }

    // Экран запуска
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.splash_screen_img),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .padding(0.dp, 16.dp, 0.dp, 0.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.words_factory),
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = DarkColor
            )
        )
    }
}