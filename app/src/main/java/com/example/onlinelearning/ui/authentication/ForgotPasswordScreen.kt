package com.example.onlinelearning.ui.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlinelearning.R
import com.example.onlinelearning.ui.base.BaseButton
import com.example.onlinelearning.ui.base.BaseTextField
import com.example.onlinelearning.ui.base.TopBar
import com.example.onlinelearning.ui.theme.BlueText
import com.example.onlinelearning.ui.theme.FontWeights
import com.example.onlinelearning.ui.theme.getPoppinsTextStyleFor
import com.example.onlinelearning.utils.extensions.getString
import com.example.onlinelearning.utils.extensions.showShortToast
import com.example.onlinelearning.utils.navigation.Authentication
import com.example.onlinelearning.viewmodel.ForgotAndResetPasswordViewModel

@Composable
fun ForgotPasswordScreen(
    navHostController: NavHostController,
    viewModel: ForgotAndResetPasswordViewModel
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val email by viewModel.email.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                onBackButtonClicked = { navHostController.navigateUp() },
                centerText = getString(R.string.forgot_password_title)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(top = 20.dp)
                .padding(horizontal = 25.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) { focusManager.clearFocus() }
        ) {
            /**
             * Email required text
             */
            Text(
                text = getString(R.string.email_required_description),
                style = getPoppinsTextStyleFor(FontWeights.FOUR_HUNDRED),
                fontSize = 16.sp,
                lineHeight = 28.sp,
                color = BlueText
            )

            Spacer(modifier = Modifier.height(74.dp))

            /**
             * Email textfield
             */
            BaseTextField(
                textFieldValue = email,
                onValueChanged = { viewModel.setEmail(it) },
                placeholder = getString(R.string.placeholder_email),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                )
            )
            
            Spacer(modifier = Modifier.height(20.dp))

            /**
             * Send code button
             */
            BaseButton(text = getString(R.string.send_code_button_text)) {
                if (email.isNotEmpty()) {
                    viewModel.validateEmail { isValid ->
                        with(context) {
                            if (isValid) {
                                navHostController.navigate(Authentication.Otp.route)
                            } else {
                                showShortToast(getString(R.string.cannot_find_email))
                            }
                        }
                    }
                } else {
                    context.showShortToast(getString(R.string.empty_credentials))
                }
            }
        }
    }
}