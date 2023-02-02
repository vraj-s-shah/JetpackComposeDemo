package com.example.onlinelearning.ui.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.onlinelearning.R
import com.example.onlinelearning.ui.base.BaseButton
import com.example.onlinelearning.ui.theme.BlueText
import com.example.onlinelearning.ui.theme.LightGray

@Composable
fun SignInSignUpScreen(navHostController: NavHostController) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 53.dp, top = 90.dp)
            .verticalScroll(scrollState)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_authentication_logo),
            contentDescription = "authentication_logo"
        )

        Image(
            painter = painterResource(id = R.drawable.ic_authentication_center),
            contentDescription = "authentication_center"
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(28.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            BaseButton(text = "Sign In") {
                navHostController.navigate(Authentication.SignIn.route)
            }

            BaseButton(
                backgroundColor = LightGray,
                text = "Sign Up",
                textColor = BlueText
            ) {
                navHostController.navigate(Authentication.SignUp.route)
            }
        }
    }
}