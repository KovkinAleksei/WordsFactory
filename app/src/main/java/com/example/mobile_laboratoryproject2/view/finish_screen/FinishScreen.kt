package com.example.mobile_laboratoryproject2.view.finish_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.ui.theme.DarkColor
import com.example.mobile_laboratoryproject2.ui.theme.DarkGrayColor
import com.example.mobile_laboratoryproject2.ui.theme.PrimaryColor
import com.example.mobile_laboratoryproject2.viewModel.training_screen.TrainingViewModel
import org.koin.androidx.compose.koinViewModel

// Экран завершения теста
@Composable
fun FinishScreen(
    correct: Int,
    incorrect: Int,
    onAgainClick: () -> Unit,
    onBackButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Кнопка возврата
        Image(
            modifier = Modifier
                .padding(16.dp, 16.dp, 0.dp, 0.dp)
                .align(Alignment.Start)
                .size(40.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = DarkGrayColor,
                    shape = CircleShape
                )
                .clickable {
                    onBackButtonClick()
                },
            contentScale = ContentScale.None,
            imageVector = ImageVector.vectorResource(R.drawable.back_arrow),
            contentDescription = null
        )

        Spacer(Modifier.weight(1f))

        // Информация о пройденном тесте
        Image(
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.finish_img),
            contentDescription = null
        )
        
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(0.dp, 32.dp, 0.dp, 0.dp),
            text = stringResource(id = R.string.finished_training),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = DarkColor
            )
        )

        val annotatedStringCorrect = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 14.sp,
                    color = DarkGrayColor
                )
            ) {
                append(stringResource(id = R.string.correct))
            }

            withStyle(
                style = SpanStyle(
                    fontSize = 14.sp,
                    color = DarkGrayColor
                )
            ) {
                append(" ${correct}")
            }
        }

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(0.dp, 8.dp, 0.dp, 0.dp),
            text = annotatedStringCorrect
        )

        val annotatedStringIncorrect = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 14.sp,
                    color = DarkGrayColor
                )
            ) {
                append(stringResource(id = R.string.incorrect))
            }

            withStyle(
                style = SpanStyle(
                    fontSize = 14.sp,
                    color = DarkGrayColor
                )
            ) {
                append(" ${incorrect}")
            }
        }

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = annotatedStringIncorrect
        )

        // Повторное прохождение теста
        AgainButton(onAgainClick)
        Spacer(Modifier.weight(1f))
    }
}

// Кнопка повторного прохождения теста
@Composable
fun AgainButton(
    onAgainClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .padding(24.dp, 32.dp, 24.dp, 0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor
        ),
        onClick = {
            onAgainClick()
        }
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = stringResource(id = R.string.again),
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        )
    }
}
