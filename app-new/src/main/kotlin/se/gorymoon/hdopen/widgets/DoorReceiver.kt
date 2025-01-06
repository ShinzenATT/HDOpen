package se.gorymoon.hdopen.widgets

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class DoorReceiver() : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget get() = DoorWidget()
}