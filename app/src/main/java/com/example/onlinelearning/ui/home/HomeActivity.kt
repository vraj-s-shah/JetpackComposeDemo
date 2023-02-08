package com.example.onlinelearning.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onlinelearning.ui.base.BottomNavigationBar
import com.example.onlinelearning.ui.theme.BottomNavigationBarShadowColor
import com.example.onlinelearning.utils.extensions.shadow
import com.example.onlinelearning.utils.navigation.BottomNavigationItem

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        navHostController = navHostController,
                        modifier = Modifier
                            .shadow(
                                color = BottomNavigationBarShadowColor,
                                blurRadius = 20.dp,
                                offsetX = 6.dp
                            )
                    )
                }
            ) { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    HomeScreens(navHostController)
                }
            }
        }
    }
}

@Composable
fun HomeScreens(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = BottomNavigationItem.Home.route) {
        composable(BottomNavigationItem.Home.route) {

        }
        composable(BottomNavigationItem.Search.route) {

        }
        composable(BottomNavigationItem.MyCourses.route) {

        }
        composable(BottomNavigationItem.Message.route) {

        }
        composable(BottomNavigationItem.Profile.route) {

        }
    }
}