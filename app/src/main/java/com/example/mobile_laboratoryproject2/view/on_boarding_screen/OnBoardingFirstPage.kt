package com.example.mobile_laboratoryproject2.view.on_boarding_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.mobile_laboratoryproject2.ui.theme.DarkGrayColor

// Первая страница приветственного экрана
@Composable
fun OnBoardingFirstPage()
{
    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 5.dp, 16.dp, 0.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.on_boarding_first_img),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp, 16.dp, 16.dp, 0.dp),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.learn_anywhere),
            style = TextStyle(
                fontSize = 24.sp,
                color = DarkColor,
                fontWeight = FontWeight.Medium
            ),
            letterSpacing = 0.05.sp
        )

        Text(
            modifier = Modifier
                .padding(16.dp, 8.dp, 16.dp, 38.dp),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.quarantine),
            style = TextStyle(
                fontSize = 14.sp,
                color = DarkGrayColor
            ),
            lineHeight = 21.sp
        )
    }
}