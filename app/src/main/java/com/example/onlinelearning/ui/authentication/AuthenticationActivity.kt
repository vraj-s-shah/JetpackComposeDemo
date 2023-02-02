package com.example.onlinelearning.ui.authentication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onlinelearning.ui.base.BaseComposeActivity

class AuthenticationActivity : BaseComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            AuthenticationScreens(navHostController)
        }
        setWhiteStatusBar()
    }
}

@Composable
fun AuthenticationScreens(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Authentication.SignInSignUp.route
    ) {
        composable(Authentication.SignInSignUp.route) {
            SignInSignUpScreen(navHostController = navHostController)
        }
        composable(Authentication.SignIn.route) {
            SignInScreen(navHostController = navHostController)
        }
        composable(Authentication.SignUp.route) {
            SignUpScreen(navHostController = navHostController)
        }
    }
}

sealed class Authentication(val route: String) {
    object SignInSignUp : Authentication("signInSignUp")
    object SignIn : Authentication("signIn")
    object SignUp : Authentication("signUp")
    object Otp : Authentication("otp")
    object ForgotPassword : Authentication("forgotPassword")
    object ResetPassword : Authentication("resetPassword")
}