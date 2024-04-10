package com.example.mobile_laboratoryproject2.view.sign_up_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.ui.theme.DarkColor
import com.example.mobile_laboratoryproject2.ui.theme.DarkGrayColor
import com.example.mobile_laboratoryproject2.ui.theme.GrayColor
import com.example.mobile_laboratoryproject2.ui.theme.PrimaryColor
import com.example.mobile_laboratoryproject2.ui.theme.SecondaryColor
import com.example.mobile_laboratoryproject2.view.common.ErrorDialog
import com.example.mobile_laboratoryproject2.viewModel.sign_up_screen.SignUpViewModel
import org.koin.androidx.compose.koinViewModel

// Экран регистрации
@Composable
fun SignUpScreen(
    onSignUp: () -> Unit,
    onSignInButtonClick: () -> Unit,
    vm: SignUpViewModel = koinViewModel()
) {
    val uiState by vm.uiState.collectAsState()
    val scroll = rememberScrollState()

    if (uiState.isSignedUp)
        onSignUp()

    Column(
        modifier = Modifier.verticalScroll(scroll)
    ) {
        // Описание экрана
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(34.dp, 24.dp, 34.dp, 0.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.cool_kids_standing),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(0.dp, 16.dp, 0.dp, 0.dp),
            text = stringResource(id = R.string.sign_up_header),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = DarkColor
            )
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                modifier = Modifier
                    .padding(0.dp, 8.dp, 0.dp, 0.dp),
                text = stringResource(id = R.string.create_account),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = DarkGrayColor
                )
            )

            Text(
                modifier = Modifier
                    .padding(4.dp, 8.dp, 0.dp, 0.dp)
                    .clickable {
                        onSignInButtonClick()
                    },
                text = stringResource(id = R.string.sign_in),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = SecondaryColor
                )
            )
        }

        // Поля ввода данных для регистрации
        TextField(
            textFieldValue = vm.name,
            handleInput = { input ->
                vm.handleNameInput(input)
            },
            placeholderText = stringResource(id = R.string.name_placeholder)
        )

        TextField(
            textFieldValue = vm.email,
            handleInput = { input ->
                vm.handleEmailInput(input)
            },
            placeholderText = stringResource(id = R.string.email_placeholder)
        )

        PasswordTextField(
            textFieldValue = vm.password,
            handleInput = { input ->
                vm.handlePasswordInput(input)
            },
            placeholderText = stringResource(id = R.string.password_placeholder)
        )

        Spacer(Modifier.weight(1f))

        // Кнопка регистрации
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
                .padding(16.dp, 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor
            ),
            onClick = {
                vm.onSignUpButtonClick()
            }
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = stringResource(id = R.string.sign_up_header),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            )
        }

        if (!uiState.areFieldValuesCorrect) {
            ErrorDialog(
                errorMessage = uiState.errorMessage,
                onDismiss = { vm.onDismiss() }
            )
        }
    }
}

// Поле ввода
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    textFieldValue: MutableState<TextFieldValue>,
    handleInput: (TextFieldValue) -> Unit,
    placeholderText: String
) {
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
                label = {
                    Text(
                        text = placeholderText,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = DarkGrayColor
                        )
                    )
                },
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

// Поле для ввода пароля
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    vm: SignUpViewModel = koinViewModel(),
    textFieldValue: MutableState<TextFieldValue>,
    handleInput: (TextFieldValue) -> Unit,
    placeholderText: String
) {

    val uiState by vm.uiState.collectAsState()
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
            visualTransformation =
            if (uiState.isHiddenPassword)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
            decorationBox = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = textFieldValue.value.text,
                    innerTextField = innerTextField,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    label = {
                        Text(
                            text = placeholderText,
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = DarkGrayColor
                            )
                        )
                    },
                    container = {
                        Row {
                        }
                    }
                )
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = DarkColor,
                fontWeight = FontWeight.Medium
            )
        )

        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(0.dp, 0.dp, 16.dp, 0.dp)
                .clip(CircleShape)
                .clickable {
                    vm.onHideButtonClick()
                },
            imageVector =
            if (uiState.isHiddenPassword)
                ImageVector.vectorResource(R.drawable.closed_eye)
            else
                ImageVector.vectorResource(R.drawable.opened_eye),
            contentDescription = null
        )
    }
}