package com.example.mobile_laboratoryproject2.view.dictionary_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.DefinitionModel
import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.WordModel
import com.example.mobile_laboratoryproject2.ui.theme.GrayColor
import com.example.mobile_laboratoryproject2.ui.theme.PrimaryColor
import com.example.mobile_laboratoryproject2.ui.theme.RedColor
import com.example.mobile_laboratoryproject2.ui.theme.SecondaryColor
import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.DictionaryViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WordInfo(
    word: WordModel,
    viewModel: DictionaryViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            if (uiState.isInDictionary)
                RemoveButton()
            else
                AddButton()
        }
    ) {
        Column {
            WordHeader(word)
            PartOfSpeech(word)
            Meanings(word)
        }
    }
}

// Заголовок информации о слове
@Composable
fun WordHeader(word: WordModel) {
    Row {
        Text(
            modifier = Modifier
                .padding(16.dp, 16.dp, 0.dp, 0.dp),
            text = word.word,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        )

        Text(
            modifier = Modifier
                .padding(16.dp, 25.dp, 0.dp, 0.dp),
            text = word.phonetics ?: "",
            style = TextStyle(
                fontSize = 14.sp,
                color = PrimaryColor
            )
        )

        Image(
            modifier = Modifier
                .padding(16.dp, 24.dp, 0.dp, 0.dp),
            imageVector = ImageVector.vectorResource(R.drawable.volume_icon),
            contentDescription = null
        )
    }
}

// Часть речи
@Composable
fun PartOfSpeech(word: WordModel) {
    Row(
        modifier = Modifier
            .padding(16.dp, 16.dp, 16.dp, 0.dp)
    ) {
        Text(
            text = stringResource(id = R.string.part_of_speech),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        )

        Text(
            modifier = Modifier
                .padding(16.dp, 0.dp, 0.dp, 0.dp)
                .align(Alignment.CenterVertically),
            text = word.partOfSpeech,
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Black
            )
        )
    }
}

// Значения слова
@Composable
fun Meanings(word: WordModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp, 16.dp, 69.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(0.dp, 11.dp, 0.dp, 0.dp),
            text = stringResource(id = R.string.meanings),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        )

        DefinitionsList(word)
    }
}

// Список определений слова
@Composable
fun DefinitionsList(
    word: WordModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp, 0.dp, 0.dp)
    ) {
        items(
            items = word.definitions,
            key = { it.definition }
        ) { word ->
            MeaningRow(word)
        }
    }
}

// Элемент списка определений слова
@Composable
fun MeaningRow(definition: DefinitionModel) {
    Column(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(16.dp),
                color = GrayColor
            )
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp, 16.dp, 16.dp, 0.dp),
            text = definition.definition,
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Black
            )
        )

        if (definition.example != null) {
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp,
                        color = SecondaryColor
                    )
                ) {
                    append(stringResource(id = R.string.example))
                }

                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                ) {
                    append(" " + definition.example)
                }
            }

            Text(
                modifier = Modifier
                    .padding(16.dp, 8.dp, 16.dp, 0.dp),
                text = annotatedString,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = SecondaryColor
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Кнопка добавления слова в словарь
@Composable
fun AddButton(
    viewModel: DictionaryViewModel = koinViewModel()
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(67.dp)
            .padding(16.dp, 3.dp, 16.dp, 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor
        ),
        onClick = {
            viewModel.onAddButtonClick()
        }
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = stringResource(id = R.string.add_to_dictionary),
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

// Кнопка удаления слова из словаря
@Composable
fun RemoveButton(
    viewModel: DictionaryViewModel = koinViewModel()
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(67.dp)
            .padding(16.dp, 3.dp, 16.dp, 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = RedColor
        ),
        onClick = {
            viewModel.onRemoveButtonClick()
        }
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = stringResource(id = R.string.remove_word),
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        )
    }
}