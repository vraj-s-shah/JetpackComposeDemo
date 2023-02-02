package com.example.onlinelearning.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlinelearning.ui.theme.BaseGreen
import com.example.onlinelearning.ui.theme.FontWeights
import com.example.onlinelearning.ui.theme.getPoppinsTextStyleFor

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = BaseGreen,
    cornerRadius: Dp = 10.dp,
    textColor: Color = Color.White,
    textStyle: TextStyle = getPoppinsTextStyleFor(FontWeights.FIVE_HUNDRED),
    textSize: TextUnit = 15.sp,
    text: String,
    onButtonClicked: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(52.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) { onButtonClicked() }
    ) {
        Text(
            text = text,
            fontSize = textSize,
            style = textStyle,
            lineHeight = 20.sp,
            color = textColor
        )
    }
}