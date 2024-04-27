package com.example.mobile_laboratoryproject2.view.training_screen

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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.ui.theme.GrayColor

// Плэйсхолдер при малом кол-ве слов для повторения
@Composable
fun TrainingPlaceholder() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .scale(-1f, 1f)
                .padding(0.dp, 46.dp, 0.dp, 0.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.dictionary_placeholder),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .padding(0.dp, 40.dp, 0.dp, 0.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.not_enough_word),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        )

        Text(
            modifier = Modifier
                .padding(0.dp, 8.dp, 0.dp, 0.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.add_words),
            style = TextStyle(
                fontSize = 14.sp,
                color = GrayColor
            )
        )
    }
}