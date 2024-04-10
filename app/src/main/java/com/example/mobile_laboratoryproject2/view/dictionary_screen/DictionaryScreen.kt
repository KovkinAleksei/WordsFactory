package com.example.mobile_laboratoryproject2.view.dictionary_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.ui.theme.DarkGrayColor
import com.example.mobile_laboratoryproject2.ui.theme.GrayColor
import com.example.mobile_laboratoryproject2.view.common.NavBar
import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.DictionaryViewModel
import com.example.mobile_laboratoryproject2.viewModel.navigation.Destination
import com.example.mobile_laboratoryproject2.view.common.ErrorDialog
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DictionaryScreen(
    onTrainingClick: () -> Unit,
    onVideoClick: () -> Unit,
    viewModel: DictionaryViewModel = koinViewModel()
)
{
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        Modifier.background(Color.White),
        bottomBar = {
            NavBar(
                currentScreen = Destination.DictionaryScreen,
                onTrainingClick = onTrainingClick,
                onVideoClick = onVideoClick
            )
        }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            SearchTextField(
                textFieldValue = viewModel.searchText,
                handleInput = { viewModel.handleSearchInput(it) }
            )

            val word = uiState.word

            if (word != null)
                WordInfo(word, onTrainingClick)
            else
                DictionaryPlaceholder()
        }
    }

    if (!uiState.isWordCorrect) {
        ErrorDialog(
            errorMessage = uiState.errorMessage,
            onDismiss = { viewModel.onDismiss() }
        )
    }
}

// Поле ввода для поиска
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    textFieldValue: MutableState<TextFieldValue>,
    handleInput: (TextFieldValue) -> Unit,
    viewModel: DictionaryViewModel = koinViewModel()
)
{
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .padding(16.dp, 24.dp, 16.dp, 0.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = GrayColor,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        BasicTextField(
            modifier = Modifier
                .weight(1f),
            value = textFieldValue.value,
            onValueChange = {
                handleInput(it)
            },
            singleLine = true,
            interactionSource = interactionSource,
            decorationBox = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = textFieldValue.value.text,
                    innerTextField = innerTextField,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    container = {
                        Row{
                        }
                    }
                )
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = DarkGrayColor,
                fontWeight = FontWeight.Medium
            )
        )
        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(0.dp, 0.dp, 18.dp, 0.dp)
                .clip(RoundedCornerShape(4.dp))
                .clickable {
                    viewModel.onSearchButtonClick()
                },
            contentScale = ContentScale.Crop,
            imageVector = ImageVector.vectorResource(R.drawable.search_icon),
            contentDescription = null
        )
    }
}