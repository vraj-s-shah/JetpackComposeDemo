package com.example.onlinelearning.ui.authentication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onlinelearning.ui.base.BaseComposeActivity
import com.example.onlinelearning.ui.home.HomeActivity
import com.example.onlinelearning.utils.Constants
import com.example.onlinelearning.utils.extensions.obtainViewModel
import com.example.onlinelearning.utils.extensions.startWithIntArgsAndFinish
import com.example.onlinelearning.utils.navigation.Authentication
import com.example.onlinelearning.viewmodel.ForgotAndResetPasswordViewModel

class AuthenticationActivity : BaseComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            AuthenticationScreens(navHostController) { userId ->
                startWithIntArgsAndFinish(
                    HomeActivity::class.java,
                    Constants.LOGGED_IN_USER_KEY to userId
                )
            }
        }
        setWhiteStatusBar()
    }
}

@Composable
fun AuthenticationScreens(
    navHostController: NavHostController,
    onStartHomeActivity: (Int) -> Unit
) {
    val forgotAndResetPasswordViewModel = obtainViewModel<ForgotAndResetPasswordViewModel>()
    NavHost(
        navController = navHostController,
        startDestination = Authentication.SignInSignUp.route
    ) {
        composable(Authentication.SignInSignUp.route) {
            SignInSignUpScreen(navHostController)
        }
        composable(Authentication.SignIn.route) {
            SignInScreen(navHostController, onStartHomeActivity)
        }
        composable(Authentication.SignUp.route) {
            SignUpScreen(navHostController, onStartHomeActivity)
        }
        composable(Authentication.ForgotPassword.route) {
            ForgotPasswordScreen(navHostController, forgotAndResetPasswordViewModel)
        }
        composable(Authentication.Otp.route) {
            OtpScreen(navHostController, forgotAndResetPasswordViewModel)
        }
        composable(Authentication.ResetPassword.route) {
            ResetPasswordScreen(
                navHostController,
                forgotAndResetPasswordViewModel,
                onStartHomeActivity
            )
        }
    }
}