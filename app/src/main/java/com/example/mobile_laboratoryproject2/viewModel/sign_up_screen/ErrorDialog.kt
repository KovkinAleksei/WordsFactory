package com.example.mobile_laboratoryproject2.viewModel.sign_up_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.ui.theme.DarkColor
import com.example.mobile_laboratoryproject2.ui.theme.PrimaryColor

// Диалоговое окно для отображения ошибки
@Composable
fun SignUpErrorDialog(
    errorMessage: Int,
    onDismiss: () -> Unit
)
{
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(20.dp, 16.dp, 16.dp, 0.dp),
                text = stringResource(id = errorMessage),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = DarkColor
                )
            )

            Row {
                Spacer(Modifier.weight(1f))
                TextButton(
                    modifier = Modifier
                        .padding(0.dp, 16.dp, 16.dp, 16.dp),
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.ok),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = PrimaryColor
                        )
                    )
                }
            }
        }
    }
}