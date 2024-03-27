package com.example.mobile_laboratoryproject2.view.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavController
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.navigation.Destination
import com.example.mobile_laboratoryproject2.ui.theme.DarkColor
import com.example.mobile_laboratoryproject2.ui.theme.DarkGrayColor
import com.example.mobile_laboratoryproject2.ui.theme.PrimaryColor
import com.example.mobile_laboratoryproject2.ui.theme.sourceColor
import com.example.mobile_laboratoryproject2.view.sign_up_screen.PasswordTextField
import com.example.mobile_laboratoryproject2.view.sign_up_screen.TextField
import com.example.mobile_laboratoryproject2.viewModel.login_screen.LoginViewModel
import com.example.mobile_laboratoryproject2.viewModel.sign_up_screen.SignUpErrorDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    vm: LoginViewModel = koinViewModel()
)
{
    val uiState by vm.uiState.collectAsState()

    if (uiState.isLoggedIn)
        navController.navigate(Destination.DictionaryScreen.name) {
            popUpTo(Destination.LoginScreen.name) {
                inclusive = true
            }
        }

    Column {
        Image(
            modifier = Modifier
                .scale(-1f, 1f)
                .fillMaxWidth()
                .padding(34.dp, 24.dp, 34.dp, 0.dp),
            painter = painterResource(id = R.drawable.cool_kids_standing),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(0.dp, 16.dp, 0.dp, 0.dp),
            text = stringResource(id = R.string.sign_in_header),
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
                text = stringResource(id = R.string.sign_in_to_account),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = DarkGrayColor
                )
            )

            Text(
                modifier = Modifier
                    .padding(4.dp, 8.dp, 0.dp, 0.dp)
                    .clickable {
                        navController.navigate(Destination.SignUpScreen.name) {
                            popUpTo(Destination.LoginScreen.name) {
                                inclusive = true
                            }
                        }
                    },
                text = stringResource(id = R.string.sign_up),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = sourceColor
                )
            )
        }

        TextField(
            textFieldValue = vm.email,
            handleInput = {
                    input -> vm.handleEmailInput(input)
            },
            placeholderText = stringResource(id = R.string.email_placeholder)
        )

        PasswordTextField(
            textFieldValue = vm.password,
            handleInput = {
                    input -> vm.handlePasswordInput(input)
            },
            placeholderText = stringResource(id = R.string.password_placeholder)
        )

        Spacer(Modifier.weight(1f))

        // Кнопка авторизации
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
                vm.onSignInButtonClick()
            }
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = stringResource(id = R.string.sign_in_header),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            )
        }

        if (!uiState.areFieldValuesCorrect) {
            SignUpErrorDialog(
                errorMessage = uiState.errorMessage,
                onDismiss = { vm.onDismiss() }
            )
        }
    }
}