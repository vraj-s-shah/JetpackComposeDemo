package com.example.onlinelearning.utils.extensions

import androidx.navigation.NavHostController

/**
 * Navigate to [route] by popping the current backstack
 */
fun NavHostController.navigateWithNoBackStack(route: String) {
    graph.startDestinationRoute?.let {
        popBackStack(it, true)
    }
    navigate(route)
}