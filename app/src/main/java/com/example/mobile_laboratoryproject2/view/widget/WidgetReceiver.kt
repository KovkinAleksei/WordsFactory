package com.example.mobile_laboratoryproject2.view.widget

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.example.mobile_laboratoryproject2.domain.use_cases.widget.WidgetUseCase

class WidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = WordsFactoryWidget()
}