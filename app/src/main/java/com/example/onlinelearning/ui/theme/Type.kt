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

private val PoppinsTypography = Typography(
    defaultFontFamily = PoppinsFontFamily,
    h1 = TextStyle(fontWeight = FontWeight.Black),
    h2 = TextStyle(fontWeight = FontWeight.Bold),
    h3 = TextStyle(fontWeight = FontWeight.SemiBold),
    h4 = TextStyle(fontWeight = FontWeight.Medium),
    h5 = TextStyle(fontWeight = FontWeight.Normal),
    h6 = TextStyle(fontWeight = FontWeight.Thin),
)

fun getPoppinsTextStyleFor(weight: FontWeights): TextStyle =
    when (weight) {
        FontWeights.HUNDRED -> PoppinsTypography.h6
        FontWeights.FOUR_HUNDRED -> PoppinsTypography.h5
        FontWeights.FIVE_HUNDRED -> PoppinsTypography.h4
        FontWeights.SIX_HUNDRED -> PoppinsTypography.h3
        FontWeights.SEVEN_HUNDRED -> PoppinsTypography.h2
        FontWeights.NINE_HUNDRED -> PoppinsTypography.h1
    }

fun getFontWeightFor(weight: FontWeights): FontWeight =
    when (weight) {
        FontWeights.HUNDRED -> FontWeight.Thin
        FontWeights.FOUR_HUNDRED -> FontWeight.Normal
        FontWeights.FIVE_HUNDRED -> FontWeight.Medium
        FontWeights.SIX_HUNDRED -> FontWeight.SemiBold
        FontWeights.SEVEN_HUNDRED -> FontWeight.Bold
        FontWeights.NINE_HUNDRED -> FontWeight.Black
    }

enum class FontWeights {
    HUNDRED,
    FOUR_HUNDRED,
    FIVE_HUNDRED,
    SIX_HUNDRED,
    SEVEN_HUNDRED,
    NINE_HUNDRED
}