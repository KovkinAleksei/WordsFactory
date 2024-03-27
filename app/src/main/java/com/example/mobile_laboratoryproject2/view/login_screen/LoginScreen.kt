package com.example.mobile_laboratoryproject2.view.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobile_laboratoryproject2.R

@Composable
fun LoginScreen(
    navController: NavController
)
{
    Column {
        Image(
            modifier = Modifier
                .scale(-1f, 1f)
                .fillMaxWidth()
                .padding(16.dp, 24.dp, 16.dp, 0.dp),
            painter = painterResource(id = R.drawable.cool_kids_standing),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}