package com.example.onlinelearning.utils.extensions

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
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
 * Show toast with given [message]
 */
fun Context.showShortToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()