package com.example.onlinelearning.utils

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.onlinelearning.viewmodel.ViewModelFactory

@Composable
fun getString(@StringRes id: Int): String = LocalContext.current.getString(id)

@Composable
inline fun<reified T: ViewModel> obtainViewModel(): T {
    val activity = LocalContext.current as Activity
    return viewModel(factory = ViewModelFactory.getInstance(activity.application))
}

fun ComponentActivity.startAndFinish(destination: Class<*>) {
    startActivity(Intent(baseContext, destination))
    finish()
}

fun NavHostController.popBackStackAndNavigate(route: String) {
    graph.startDestinationRoute?.let {
        popBackStack(it, true)
    }
    navigate(route)
}