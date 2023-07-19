package com.example.donuts.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/*val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)*/

val Primary = Color(0xFFFF7074)
val OnPrimary = Color(0xFFFFFFFF)
val Background = Color(0xFFFED8DF)
val OnBackground87 = Color(0xDEFED8DF)
val OnBackground60 = Color(0x99FED8DF)
val OnBackground38 = Color(0x61FED8DF)
val Secondary = Color(0xFFD7E4F6)
val OnSecondary = Color(0xFFFFFFFF)
val OnCard = Color(0x99000000)
val Card = Color(0xFFFFFFFF)
val textColor = Color(0xFFFF9494)

@Immutable
data class CustomColorsPalette(
    val primary: Color = Color.Unspecified,
    val onPrimary: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val onSecondary: Color = Color.Unspecified,
    val onBackground87: Color = Color.Unspecified,
    val onBackground60: Color = Color.Unspecified,
    val onBackground38: Color = Color.Unspecified,
    val card: Color = Color.Unspecified,
    val onCard: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val textColor: Color = Color.Unspecified,
)