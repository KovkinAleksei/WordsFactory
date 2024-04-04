package com.example.mobile_laboratoryproject2.view.finish_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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

// Экран завершения теста
@Composable
fun FinishScreen(
    correct: Int,
    incorrect: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            modifier = Modifier
                .padding(0.dp, 68.dp, 0.dp, 0.dp)
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
    }
}