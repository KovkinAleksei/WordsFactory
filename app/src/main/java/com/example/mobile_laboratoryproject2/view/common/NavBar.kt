package com.example.mobile_laboratoryproject2.view.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.ui.theme.GrayColor
import com.example.mobile_laboratoryproject2.ui.theme.PrimaryColor
import com.example.mobile_laboratoryproject2.viewModel.common.NavBarItems
import com.example.mobile_laboratoryproject2.viewModel.common.NavBarViewModel
import org.koin.androidx.compose.koinViewModel

// Нижняя навигационная панель
@Composable
fun NavBar(
    viewModel: NavBarViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
            .border(
                width = 1.dp,
                color = GrayColor,
                shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp)
            )
            .padding(16.dp, 0.dp)
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Элемент Dictionary
        NavigationElement(
            icon = if (uiState.selectedItem == NavBarItems.Dictionary)
                R.drawable.dictonary_selected
            else
                R.drawable.dictionary_unselected,
            title = R.string.dictionary,
            isSelected = uiState.selectedItem == NavBarItems.Dictionary,
            onClick = {
                viewModel.onDictionaryClick()
            }
        )

        // Элемент Training
        NavigationElement(
            icon = if (uiState.selectedItem == NavBarItems.Training)
                R.drawable.training_selected
            else
                R.drawable.training_unselected,
            title = R.string.training,
            isSelected = uiState.selectedItem == NavBarItems.Training,
            onClick = {
                viewModel.onTrainingClick()
            }
        )

        // Элемент Video
        NavigationElement(
            icon = if (uiState.selectedItem == NavBarItems.Video)
                R.drawable.video_selected
            else
                R.drawable.video_unselected,
            title = R.string.video,
            isSelected = uiState.selectedItem == NavBarItems.Video,
            onClick = {
                viewModel.onVideoClick()
            }
        )
    }
}

// Элемент нижней навигационной панели
@Composable
fun NavigationElement(
    icon: Int,
    title: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .width(62.dp)
            .clip(RoundedCornerShape(6.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            }
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(0.dp, 20.dp, 0.dp, 0.dp),
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(0.dp, 4.dp, 0.dp, 38.dp),
            text = stringResource(id = title),
            style = TextStyle(
                fontSize = 14.sp,
                color = if (isSelected)
                    PrimaryColor
                else
                    GrayColor
            )
        )
    }
}