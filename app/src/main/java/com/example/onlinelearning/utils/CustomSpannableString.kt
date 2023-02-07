package com.example.onlinelearning.utils

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.onlinelearning.ui.theme.FontWeights
import com.example.onlinelearning.ui.theme.PoppinsFontFamily
import com.example.onlinelearning.ui.theme.getFontWeightFor

data class SpannedString(
    val text: String,
    val fontFamily: FontFamily = PoppinsFontFamily,
    val fontWeight: FontWeights = FontWeights.FOUR_HUNDRED,
    val color: Color = Color.Black,
    val size: TextUnit = 15.sp,
    val fontStyle: FontStyle = FontStyle.Normal,
    val onClick: () -> Unit = { }
)

@Composable
fun CustomSpannableString(
    modifier: Modifier = Modifier,
    vararg spannedStrings: SpannedString
) {
    val annotatedString = buildAnnotatedString {
        spannedStrings.forEach {
            with(it) {
                pushStringAnnotation(text, "")
                withStyle(
                    SpanStyle(
                        fontFamily = fontFamily,
                        fontWeight = getFontWeightFor(fontWeight),
                        fontSize = size,
                        color = color,
                        fontStyle = fontStyle
                    )
                ) { append(text) }
                pop()
            }
        }
    }

    ClickableText(
        text = annotatedString,
        modifier = modifier
    ) {
        spannedStrings.forEachIndexed { index, string ->
            annotatedString
                .getStringAnnotations(string.text, it, it)
                .firstOrNull()
                ?.let {
                    spannedStrings[index].onClick.invoke()
                }
        }
    }
}