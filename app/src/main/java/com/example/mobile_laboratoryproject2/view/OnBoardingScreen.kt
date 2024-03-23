package com.example.mobile_laboratoryproject2.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.ui.theme.DarkColor
import com.example.mobile_laboratoryproject2.ui.theme.DarkGrayColor
import com.example.mobile_laboratoryproject2.ui.theme.PaginationColor
import com.example.mobile_laboratoryproject2.ui.theme.PrimaryColor
import com.example.mobile_laboratoryproject2.ui.theme.SecondaryColor

import com.example.mobile_laboratoryproject2.viewModel.OnBoardingViewModel

// Приветственный экран
@Composable
fun OnBoardingScreen(vm: OnBoardingViewModel = viewModel())
{
    Column {
        Text(
            modifier = Modifier
                .align(Alignment.End)
                .padding(0.dp, 24.dp, 16.dp, 0.dp),
            text = stringResource(id = R.string.skip),
            style = TextStyle(
                color = DarkGrayColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )

        FirstOnBoardScreen()
        Pagination()
        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(24.dp, 0.dp, 24.dp, 24.dp)
                .defaultMinSize(1.dp, 1.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor
            ),
            onClick = {
                vm.onNextClick()
            }
        ) {
            Text(
                text = stringResource(id = R.string.next),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

// Первая страница приветственного экрана
@Composable
fun FirstOnBoardScreen()
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
                .padding(16.dp, 8.dp, 16.dp, 0.dp),
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

// Индикатор выбранной страницы
@Composable
fun Pagination(vm: OnBoardingViewModel = viewModel())
{
    val uiState = vm.uiState.collectAsState()

    Row(
        modifier = Modifier
            .padding(0.dp, 30.dp, 0.dp, 0.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        for (i in 1..uiState.value.pagesCount){
            if (i == uiState.value.currentPage){
                SelectedPage()
            }
            else {
                UnselectedPage()
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

// Выбранная страница
@Composable
fun SelectedPage()
{
    Box(modifier = Modifier
        .size(16.dp, 6.dp)
        .clip(RoundedCornerShape(4.dp))
        .background(SecondaryColor)
    )
}

// Невыбранная страница
@Composable
fun UnselectedPage()
{
    Box(
        modifier = Modifier
            .size(6.dp, 6.dp)
            .clip(CircleShape)
            .background(PaginationColor)
    )
}