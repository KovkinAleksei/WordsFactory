package com.example.mobile_laboratoryproject2.view.dictionary_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.ui.theme.DarkGrayColor
import com.example.mobile_laboratoryproject2.ui.theme.GrayColor
import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.DictionaryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DictionaryScreen(
    navController: NavHostController,
    viewModel: DictionaryViewModel = koinViewModel()
)
{
    val uiState by viewModel.uiState.collectAsState()

    Column {
        SearchTextField(
            textFieldValue = viewModel.searchText,
            handleInput = { viewModel.handleSearchInput(it) }
        )

        val word = uiState.word

        if (word != null)
            WordInfo(word)
        else
            DictionaryPlaceholder()
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