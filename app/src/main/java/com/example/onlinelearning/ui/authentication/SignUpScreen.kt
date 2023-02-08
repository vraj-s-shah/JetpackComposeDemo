package com.example.onlinelearning.ui.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlinelearning.R
import com.example.onlinelearning.ui.base.BaseButton
import com.example.onlinelearning.ui.base.BaseTextField
import com.example.onlinelearning.ui.base.TopBar
import com.example.onlinelearning.ui.theme.BaseGreen
import com.example.onlinelearning.ui.theme.BlueText
import com.example.onlinelearning.ui.theme.FontWeights
import com.example.onlinelearning.ui.theme.getPoppinsTextStyleFor
import com.example.onlinelearning.utils.CredentialsValidator
import com.example.onlinelearning.utils.CustomSpannableString
import com.example.onlinelearning.utils.SpannedString
import com.example.onlinelearning.utils.extensions.getString
import com.example.onlinelearning.utils.extensions.navigateWithNoBackStack
import com.example.onlinelearning.utils.extensions.obtainViewModel
import com.example.onlinelearning.utils.extensions.showShortToast
import com.example.onlinelearning.utils.navigation.Authentication
import com.example.onlinelearning.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    navHostController: NavHostController,
    onSignUpSuccess: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val viewModel = obtainViewModel<SignUpViewModel>()
    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val isPasswordHidden by viewModel.isPasswordHidden.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                onBackButtonClicked = {
                    navHostController.navigateWithNoBackStack(Authentication.SignInSignUp.route)
                },
                centerText = getString(R.string.sign_up_title)
            )
        }
    ) { padding ->
        /**
         * Parent view
         */
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 25.dp)
                .verticalScroll(scrollState)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) { focusManager.clearFocus() }
        ) {
            /**
             * Create account title
             */
            Text(
                text = getString(R.string.create_account_title),
                style = getPoppinsTextStyleFor(FontWeights.FIVE_HUNDRED),
                fontSize = 30.sp,
                lineHeight = 39.sp,
                color = BlueText
            )

            /**
             * TextFields and sign up button block
             */
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
            ) {
                BaseTextField(
                    textFieldValue = name,
                    onValueChanged = { viewModel.setName(it) },
                    placeholder = getString(R.string.placeholder_name),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Next) }
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                BaseTextField(
                    textFieldValue = email,
                    onValueChanged = { viewModel.setEmail(it) },
                    placeholder = getString(R.string.placeholder_email),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Next) }
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                BaseTextField(
                    textFieldValue = password,
                    onValueChanged = { viewModel.setPassword(it) },
                    hideText = isPasswordHidden,
                    placeholder = getString(R.string.placeholder_password),
                    rightButtonIcon = R.drawable.ic_password_eye,
                    onRightButtonClicked = { viewModel.setIsPasswordHidden(!isPasswordHidden) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )

                Spacer(modifier = Modifier.height(35.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    BaseButton(text = getString(R.string.sign_up)) {
                        viewModel.validateUserCredentials {
                            context.apply {
                                when (it) {
                                    CredentialsValidator.EmptyCredentials ->
                                        showShortToast(getString(R.string.empty_credentials))
                                    CredentialsValidator.InvalidEmailAddress ->
                                        showShortToast(getString(R.string.invalid_email))
                                    CredentialsValidator.InvalidPassword ->
                                        showShortToast(getString(R.string.invalid_password))
                                    CredentialsValidator.UserAlreadyExists ->
                                        showShortToast(getString(R.string.user_already_added))
                                    null ->
                                        showShortToast(getString(R.string.please_try_again))
                                    is CredentialsValidator.Success -> {
                                        viewModel.setUserLoggedIn()
                                        onSignUpSuccess(it.id)
                                    }
                                    else -> {}
                                }
                            }
                        }
                    }

                    CustomSpannableString(
                        spannedStrings = arrayOf(
                            SpannedString(
                                text = getString(R.string.already_have_an_account),
                                fontWeight = FontWeights.FOUR_HUNDRED,
                                color = BlueText,
                                size = 13.sp
                            ),
                            SpannedString(
                                text = getString(R.string.sign_in),
                                fontWeight = FontWeights.FIVE_HUNDRED,
                                color = BaseGreen,
                                size = 13.sp,
                                onClick = {
                                    navHostController.navigate(Authentication.SignIn.route) {
                                        popUpTo(Authentication.SignInSignUp.route) {
                                            inclusive = false
                                        }
                                        launchSingleTop = true
                                    }
                                }
                            )
                        )
                    )
                }
            }

            /**
             * Google or apple login option block
             */
            GoogleOrAppleLoginBlock()
        }
    }
}