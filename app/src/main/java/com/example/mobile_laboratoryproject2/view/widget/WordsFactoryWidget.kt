package com.example.mobile_laboratoryproject2.view.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.color.ColorProvider
import androidx.glance.currentState
import androidx.glance.layout.Box
import androidx.glance.layout.ContentScale
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.domain.use_cases.widget.PreferencesKeys
import com.example.mobile_laboratoryproject2.ui.theme.GrayColor
import com.example.mobile_laboratoryproject2.viewModel.widget.WidgetViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordsFactoryWidget : GlanceAppWidget() {
    private val words = "Words"
    private val myDictionary = "My Dictionary"
    private val wordsFactory = "WordsFactory"
    private val alreadyRemember = "I already remember"

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val viewModel = WidgetViewModel()

        provideContent {
            GlanceTheme {
                Content(context, id, viewModel)
            }
        }
    }

    // Разметка виджета
    @Composable
    private fun Content(
        context: Context,
        id: GlanceId,
        viewModel: WidgetViewModel
    ) {
        // Получение данных для виджета
        LaunchedEffect(currentState()) {
            CoroutineScope(Dispatchers.IO).launch {
                updateAppWidgetState(context, id) {
                    viewModel.updateData()
                    it[intPreferencesKey(PreferencesKeys.WORDS_AMOUNT)] = viewModel.wordsAmount
                    it[intPreferencesKey(PreferencesKeys.LEARNED_WORDS)] = viewModel.learnedWords
                }

                update(context, id)
            }
        }

        val wordsAmount = currentState<Preferences>().toMutablePreferences()[intPreferencesKey(PreferencesKeys.WORDS_AMOUNT)]
        val learnedWords = currentState<Preferences>().toMutablePreferences()[intPreferencesKey(PreferencesKeys.LEARNED_WORDS)]

        androidx.glance.layout.Column(
            modifier = GlanceModifier
                .fillMaxWidth()
                .cornerRadius(16.dp)
                .background(ColorProvider(Color.White, Color.White))
        ) {
            // Заголовок
            androidx.glance.layout.Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .height(30.dp)
            ) {
                Box {
                    Image(
                        modifier = GlanceModifier
                            .fillMaxSize(),
                        provider = ImageProvider(R.drawable.widget_gradient),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )

                    Text(
                        modifier = GlanceModifier
                            .padding(8.dp, 4.dp, 0.dp, 4.dp),
                        text = wordsFactory,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = ColorProvider(Color.White, Color.White)
                        )
                    )
                }
            }

            // Кол-во слов в словаре
            androidx.glance.layout.Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = GlanceModifier
                        .padding(8.dp, 8.dp, 0.dp, 0.dp),
                    text = myDictionary,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        color = ColorProvider(Color.Black, Color.Black)
                    )
                )

                androidx.glance.layout.Spacer(GlanceModifier.defaultWeight())

                Text(
                    modifier = GlanceModifier
                        .padding(0.dp, 8.dp, 8.dp, 0.dp),
                    text = "$wordsAmount $words",
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = ColorProvider(GrayColor, GrayColor)
                    )
                )
            }

            // Кол-во выученных слов
            androidx.glance.layout.Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 8.dp)
            ) {
                Text(
                    modifier = GlanceModifier
                        .padding(8.dp, 8.dp, 0.dp, 0.dp),
                    text = alreadyRemember,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        color = ColorProvider(Color.Black, Color.Black)
                    )
                )

                androidx.glance.layout.Spacer(GlanceModifier.defaultWeight())

                Text(
                    modifier = GlanceModifier
                        .padding(0.dp, 8.dp, 8.dp, 0.dp),
                    text = "$learnedWords $words",
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = ColorProvider(GrayColor, GrayColor)
                    )
                )
            }
        }
    }
}