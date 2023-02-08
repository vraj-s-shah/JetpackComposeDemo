package com.example.onlinelearning.utils.extensions

import android.app.Activity
import android.content.Context
import android.graphics.BlurMaskFilter
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.onlinelearning.viewmodel.ViewModelFactory

/**
 * Runs the [block] with [Context]
 */
@Composable
private fun<T: Any> withAppContext(block: Context.() -> T): T =
    with(LocalContext.current) { block() }

/**
 * Get the string from string resource [id]
 */
@Composable
fun getString(@StringRes id: Int): String =
    withAppContext { getString(id) }

/**
 * Get the string from string resource [id] with [arguments]
 */
@Composable
fun getStringWithArgs(@StringRes id: Int, vararg arguments: Any): String =
    withAppContext { getString(id, arguments) }

/**
 * Get the viewmodel for required type [T]
 */
@Composable
inline fun<reified T: ViewModel> obtainViewModel(): T {
    val activity = LocalContext.current as Activity
    return viewModel(factory = ViewModelFactory.getInstance(activity.application))
}

/**
 * Show toast with given [message] in non composable functions
 */
fun Context.showShortToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

/**
 * Show toast with given [message] in composable functions
 */
@Composable
fun ShowShortToast(message: String) =
    withAppContext { Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }

/**
 * Focus manager extension to move focus left
 */
fun FocusManager.moveLeft() = moveFocus(FocusDirection.Left)

/**
 * Focus manager extension to move focus right
 */
fun FocusManager.moveRight() = moveFocus(FocusDirection.Right)

/**
 * Custom shadow to apply x-offset, y-offset and blur radius
 */
fun Modifier.shadow(
    color: Color = Color.Black,
    borderRadius: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0f.dp,
) = then(
    drawBehind {
        drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = (size.width + spreadPixel)
            val bottomPixel = (size.height + spreadPixel)

            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }

            frameworkPaint.color = color.toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint = paint
            )
        }
    }
)