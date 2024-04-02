package com.example.mobile_laboratoryproject2.view.training_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.DictionaryViewModel
import com.example.mobile_laboratoryproject2.viewModel.navigation.Destination
import com.example.mobile_laboratoryproject2.viewModel.training_screen.TrainingViewModel
import org.koin.androidx.compose.koinViewModel

// Экран повторения слов
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrainingScreen(
    onDictionaryClick: () -> Unit,
    viewModel: TrainingViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            Column {
                if (!uiState.isStarted)
                    StartButton()

                NavBar(
                    currentScreen = Destination.TrainingScreen,
                    onDictionaryClick = onDictionaryClick
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                ) {
                    append(stringResource(id = R.string.there_are))
                }

                withStyle(
                    style = SpanStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = PrimaryColor
                    )
                ) {
                    append(" ${uiState.wordsAmount} ")
                }

                withStyle(
                    style = SpanStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                ) {
                    append(stringResource(id = R.string.words_in_dictionary))
                }
            }

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(70.dp, 160.dp, 70.dp, 0.dp),
                textAlign = TextAlign.Center,
                text = annotatedString
            )

            Text(
                modifier = Modifier
                    .padding(70.dp, 40.dp, 70.dp, 0.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.start_training),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            )

            if (uiState.isStarted)
                Countdown()
        }
    }
}

// Кнопка запуска повтора слов
@Composable
fun StartButton(
    viewModel: TrainingViewModel = koinViewModel()
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(24.dp, 0.dp, 24.dp, 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor
        ),
        onClick = {
            viewModel.onStartButtonClick()
        }
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = stringResource(id = R.string.start),
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

// Таймер с отсчётом
@Composable
fun Countdown(
    viewModel: TrainingViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(0.dp, 80.dp, 0.dp, 0.dp)
    ){
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(66.dp),
            color = uiState.timerColor,
            strokeWidth = 5.dp,
            strokeCap = StrokeCap.Round,
            progress = {
                uiState.countDownProgress
            }

        )

        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = uiState.timer.toString(),
            style = TextStyle(
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                color = uiState.timerColor
            )
        )
    }
}