package com.example.mobile_laboratoryproject2.view.widget

import android.content.Context
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.color.ColorProvider
import androidx.glance.layout.Box
import androidx.glance.layout.ContentScale
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.ui.theme.GrayColor
import com.example.mobile_laboratoryproject2.ui.theme.widgetEndColor
import com.example.mobile_laboratoryproject2.ui.theme.widgetStartColor

class WordsFactoryWidget : GlanceAppWidget() {
    private val wordsAmount = "Words"
    private val myDictionary = "My Dictionary"
    private val wordsFactory = "WordsFactory"
    private val alreadyRemember = "I already remember"

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                Content()
            }
        }
    }

    // Разметка виджета
    @Composable
    private fun Content() {
        androidx.glance.layout.Column(
            modifier = GlanceModifier
                .fillMaxWidth()
                .background(ColorProvider(Color.White, Color.White))
        ) {
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
                    text = "3125 $wordsAmount",
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
                    text = "41 $wordsAmount",
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = ColorProvider(GrayColor, GrayColor)
                    )
                )
            }
        }
    }
}