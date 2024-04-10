package com.example.mobile_laboratoryproject2.view.training_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.ui.theme.PrimaryColor
import com.example.mobile_laboratoryproject2.view.common.NavBar
import com.example.mobile_laboratoryproject2.viewModel.navigation.Destination
import com.example.mobile_laboratoryproject2.viewModel.training_screen.TrainingViewModel
import org.koin.androidx.compose.koinViewModel

// Экран повторения слов
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrainingScreen(
    onDictionaryClick: () -> Unit,
    onVideoClick: () -> Unit,
    onTrainingStart: () -> Unit,
    viewModel: TrainingViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(null) {
        viewModel.uiState.collect{
            if (it.isCountdownCompleted) {
                onTrainingStart()
            }
        }
    }

    Scaffold(
        bottomBar = {
            Column {
                if (!uiState.isStarted && uiState.areEnoughWords)
                    StartButton()

                NavBar(
                    currentScreen = Destination.TrainingScreen,
                    onDictionaryClick = onDictionaryClick,
                    onVideoClick = onVideoClick
                )
            }
        }
    ) {
        if (uiState.areEnoughWords)
            StartTrainingScreen()
        else
            TrainingPlaceholder()
    }
}