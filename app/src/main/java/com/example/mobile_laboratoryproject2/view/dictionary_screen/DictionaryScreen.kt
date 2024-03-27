package com.example.mobile_laboratoryproject2.view.dictionary_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mobile_laboratoryproject2.ui.theme.DarkColor
import com.example.mobile_laboratoryproject2.ui.theme.GrayColor
import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.DictionaryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DictionaryScreen(
    navController: NavHostController,
    viewModel: DictionaryViewModel = koinViewModel()
)
{
    Column {
        SearchTextField(
            textFieldValue = viewModel.searchText,
            handleInput = { viewModel.onSearchTextChanged() }
        )
    }
}

// Поле ввода для поиска
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    textFieldValue: MutableState<TextFieldValue>,
    handleInput: (TextFieldValue) -> Unit
)
{
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 16.dp, 16.dp, 0.dp),
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
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 1.dp,
                                color = GrayColor,
                                shape = RoundedCornerShape(12.dp)
                            )
                    )
                }
            )
        },
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = DarkColor,
            fontWeight = FontWeight.Medium
        )
    )
}