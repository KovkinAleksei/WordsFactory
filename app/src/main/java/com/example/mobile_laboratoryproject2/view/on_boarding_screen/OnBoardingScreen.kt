package com.example.mobile_laboratoryproject2.view.on_boarding_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.ui.theme.DarkGrayColor
import com.example.mobile_laboratoryproject2.ui.theme.PaginationColor
import com.example.mobile_laboratoryproject2.ui.theme.PrimaryColor
import com.example.mobile_laboratoryproject2.ui.theme.SecondaryColor
import com.example.mobile_laboratoryproject2.viewModel.on_boarding_screen.OnBoardingViewModel
import org.koin.androidx.compose.koinViewModel

// Приветственный экран
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onSkipButtonClick: () -> Unit,
    onStartButtonClick: () -> Unit,
    vm: OnBoardingViewModel = koinViewModel()
) {
    val uiState by vm.uiState.collectAsState()

    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        // Кнопка пропуска
        Text(
            modifier = Modifier
                .align(Alignment.End)
                .padding(0.dp, 24.dp, 16.dp, 0.dp)
                .clickable {
                    onSkipButtonClick()
                },
            text = stringResource(id = R.string.skip),
            style = TextStyle(
                color = DarkGrayColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )

        // Пролистывающиеся страницы приветственного экрана
        val pagerState = rememberPagerState(
            pageCount = { uiState.pagesCount }
        )

        LaunchedEffect(uiState.currentPage) {
            pagerState.animateScrollToPage(uiState.currentPage - 1)
        }

        LaunchedEffect(pagerState.currentPage) {
            vm.onPagerScroll(pagerState.currentPage + 1)
        }

        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState
        ) { page ->
            when (page) {
                0 -> OnBoardingFirstPage()
                1 -> OnBoardingSecondPage()
                else -> OnBoardingThirdPage()
            }
        }

        // Индикатор текущей страницы
        Pagination()

        // Кнопка продолжения
        if (uiState.currentPage != uiState.pagesCount)
            NextButton()
        else
            LetsStartButton(onStartButtonClick)
    }
}

// Индикатор выбранной страницы
@Composable
fun Pagination(vm: OnBoardingViewModel = koinViewModel()) {
    val uiState by vm.uiState.collectAsState()

    Row(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 38.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        for (i in 1..uiState.pagesCount) {
            if (i == uiState.currentPage) {
                SelectedPage()
            } else {
                UnselectedPage()
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

// Пометка выбранной страницы
@Composable
fun SelectedPage() {
    Box(
        modifier = Modifier
            .size(16.dp, 6.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(SecondaryColor)
    )
}

// Пометка невыбранной страницы
@Composable
fun UnselectedPage() {
    Box(
        modifier = Modifier
            .size(6.dp, 6.dp)
            .clip(CircleShape)
            .background(PaginationColor)
    )
}

// Кнопка перехода на следующую страницу
@Composable
fun NextButton(vm: OnBoardingViewModel = koinViewModel()) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
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

// Кнопка перехода с приветственного экрана
@Composable
fun LetsStartButton(onStartButtonClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(24.dp, 0.dp, 24.dp, 24.dp)
            .defaultMinSize(1.dp, 1.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor
        ),
        onClick = {
            onStartButtonClick()
        }
    ) {
        Text(
            text = stringResource(id = R.string.lets_start),
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        )
    }
}