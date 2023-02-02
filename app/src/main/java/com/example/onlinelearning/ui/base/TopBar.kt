package com.example.onlinelearning.ui.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlinelearning.R
import com.example.onlinelearning.ui.theme.BlueText
import com.example.onlinelearning.ui.theme.FontWeights
import com.example.onlinelearning.ui.theme.LightGray
import com.example.onlinelearning.ui.theme.getPoppinsTextStyleFor

@Composable
fun TopBar(
    onBackButtonClicked: () -> Unit,
    centerText: String? = null,
    rightButtonText: String? = null,
    onRightButtonClicked: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .padding(top = 20.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .align(Alignment.CenterStart)
                .background(LightGray)
                .clickable { onBackButtonClicked() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "backButton",
            )
        }
        centerText?.let {
            Text(
                text = it,
                color = BlueText,
                fontSize = 18.sp,
                lineHeight = 40.sp,
                style = getPoppinsTextStyleFor(FontWeights.FIVE_HUNDRED),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        rightButtonText?.let {
            Text(
                text = it,
                color = BlueText,
                fontSize = 18.sp,
                lineHeight = 26.sp,
                style = getPoppinsTextStyleFor(FontWeights.FOUR_HUNDRED),
                modifier = Modifier
                    .clickable { onRightButtonClicked() }
                    .align(Alignment.CenterEnd)
            )
        }
    }
}