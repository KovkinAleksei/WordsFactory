package com.example.mobile_laboratoryproject2.view.dictionary_screen

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.ui.theme.DarkColor
import com.example.mobile_laboratoryproject2.ui.theme.DarkGrayColor

@Composable
fun DictionaryPlaceholder()
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .padding(0.dp, 46.dp, 0.dp, 0.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.dictionary_placeholder),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .padding(0.dp, 32.dp, 0.dp, 0.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id =R.string.no_word),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = DarkColor
            )
        )

        Text(
            modifier = Modifier
                .padding(0.dp, 8.dp, 0.dp, 0.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.input_something),
            style = TextStyle(
                fontSize = 14.sp,
                color = DarkGrayColor
            )
        )
    }
}