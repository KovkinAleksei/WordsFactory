package com.example.mobile_laboratoryproject2.view.question_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_laboratoryproject2.ui.theme.ChosenOptionBorderColor
import com.example.mobile_laboratoryproject2.ui.theme.ChosenOptionFillColor
import com.example.mobile_laboratoryproject2.ui.theme.DarkColor
import com.example.mobile_laboratoryproject2.ui.theme.DarkGrayColor
import com.example.mobile_laboratoryproject2.ui.theme.GrayColor
import com.example.mobile_laboratoryproject2.ui.theme.ProgressBarGradientEnd
import com.example.mobile_laboratoryproject2.ui.theme.ProgressBarGradientStart
import com.example.mobile_laboratoryproject2.viewModel.question_screen.AnswerOption
import com.example.mobile_laboratoryproject2.viewModel.question_screen.QuestionViewModel
import org.koin.androidx.compose.koinViewModel

// Экран прохождения теста
@Composable
fun QuestionScreen(
    onTestFinish: (Int, Int) -> Unit,
    viewModel: QuestionViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(null) {
        viewModel.uiState.collect{
            if (it.isFinished)
                onTestFinish(viewModel.getCorrectCount(), viewModel.getIncorrectCount())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Номер текущего вопроса
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(0.dp, 32.dp, 0.dp, 0.dp),
            text = "${uiState.currentQuestion} of ${uiState.questionsAmount}",
            style = TextStyle(
                fontSize = 16.sp,
                color = DarkGrayColor
            )
        )

        // Опеределение слова
        Text(
            modifier = Modifier
                .padding(16.dp, 16.dp, 16.dp, 8.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            text = uiState.question?.definition ?: "",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = DarkColor
            )
        )

        // Варианты ответа
        uiState.question?.answerOptions?.forEach {
            AnswerOptions(option = it)
        }

        Spacer(Modifier.weight(1f))
        QuestionsProgress()
    }
}

// Варианты ответа
@Composable
fun AnswerOptions(
    option: AnswerOption,
    viewModel: QuestionViewModel = koinViewModel()
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .padding(16.dp, 16.dp, 16.dp, 0.dp)
            .height(58.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (option.isChosen)
                    ChosenOptionFillColor
                else
                    Color.White
            )
            .border(
                width = 1.dp,
                color = if (option.isChosen)
                    ChosenOptionBorderColor
                else
                    GrayColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                viewModel.onAnswerOptionChoose(option)
            }
    ) {
        Text(
            modifier = Modifier
                .padding(24.dp, 0.dp, 0.dp, 0.dp)
                .align(Alignment.CenterVertically),
            text = option.optionName,
            style = TextStyle(
                fontSize = 16.sp,
                color = DarkColor
            )
        )

        Text(
            modifier = Modifier
                .padding(16.dp, 0.dp, 0.dp, 0.dp)
                .align(Alignment.CenterVertically),
            text = option.word,
            style = TextStyle(
                fontSize = 16.sp,
                color = DarkColor
            )
        )
    }
}

// Прогресс прохождения теста
@Composable
fun QuestionsProgress(
    viewModel: QuestionViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 32.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(GrayColor)
            .height(5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(uiState.timerValue)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(ProgressBarGradientStart, ProgressBarGradientEnd)
                    )
                )
        )
    }
}