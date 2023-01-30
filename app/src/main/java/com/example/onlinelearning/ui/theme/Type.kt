package com.example.onlinelearning.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.onlinelearning.R

val PoppinsFontFamily = FontFamily(
    Font(R.font.poppins_thin, FontWeight.Thin),
    Font(R.font.poppins_extra_light, FontWeight.ExtraLight),
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_extra_bold, FontWeight.ExtraBold),
    Font(R.font.poppins_black, FontWeight.Black)
)
// Set of Material typography styles to start with
val PoppinsTypography = Typography(
    defaultFontFamily = PoppinsFontFamily,
    h1 = TextStyle(fontWeight = FontWeight.Black),
    h2 = TextStyle(fontWeight = FontWeight.Bold),
    h3 = TextStyle(fontWeight = FontWeight.SemiBold),
    h4 = TextStyle(fontWeight = FontWeight.Medium),
    h5 = TextStyle(fontWeight = FontWeight.Normal),
    h6 = TextStyle(fontWeight = FontWeight.Thin),
)