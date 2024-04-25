package com.example.rainbowapp

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class Rainbow(
    @StringRes val colorName: Int,
    //@DrawableRes val imageResourceId: Int

    val colorValue: Color //use color instead of Picture
)

val rainbows = listOf(
    Rainbow(R.string.red_text, Color.Red),
    Rainbow(R.string.orange_text, Color(0xFFFFA500)),
    Rainbow(R.string.yellow_text, Color.Yellow),
    Rainbow(R.string.green_text, Color.Green),
    Rainbow(R.string.blue_text, Color.Blue),
    Rainbow(R.string.Dark_blue_text, Color(0xFF00008B)),
    Rainbow(R.string.Violate_text, Color(0xFF8A2BE2)),
)