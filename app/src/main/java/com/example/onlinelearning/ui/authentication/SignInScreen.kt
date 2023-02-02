package com.example.onlinelearning.ui.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlinelearning.R
import com.example.onlinelearning.ui.base.BaseButton
import com.example.onlinelearning.ui.base.BaseLeftImageButton
import com.example.onlinelearning.ui.base.BaseTextField
import com.example.onlinelearning.ui.base.TopBar
import com.example.onlinelearning.ui.theme.BaseGreen
import com.example.onlinelearning.ui.theme.BlueText
import com.example.onlinelearning.ui.theme.FontWeights
import com.example.onlinelearning.ui.theme.getFontWeightFor
import com.example.onlinelearning.ui.theme.getPoppinsTextStyleFor
import com.example.onlinelearning.utils.CustomSpannableString
import com.example.onlinelearning.utils.SpannedString
import com.example.onlinelearning.utils.getString
import com.example.onlinelearning.utils.obtainViewModel
import com.example.onlinelearning.utils.popBackStackAndNavigate
import com.example.onlinelearning.viewmodel.SignInViewModel

@Composable
fun SignInScreen(navHostController: NavHostController) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    val viewModel = obtainViewModel<SignInViewModel>()
    val name by viewModel.name.collectAsState()
    val password by viewModel.password.collectAsState()
    val isPasswordHidden by viewModel.isPasswordHidden.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                onBackButtonClicked = {
                    navHostController.popBackStackAndNavigate(Authentication.SignInSignUp.route)
                },
                centerText = getString(R.string.sign_in_title)
            )
        }
    ) { padding ->
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
            Text(
                text = getString(R.string.welcome_back_title),
                style = getPoppinsTextStyleFor(FontWeights.FIVE_HUNDRED),
                fontSize = 30.sp,
                lineHeight = 39.sp,
                color = BlueText
            )

            Column {
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
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = getString(R.string.forgot_password),
                    style = getPoppinsTextStyleFor(FontWeights.FIVE_HUNDRED),
                    fontSize = 13.sp,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Right,
                    color = BaseGreen,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, bottom = 40.dp)
            ) {
                BaseButton(
                    text = getString(id = R.string.sign_in)
                ) {
                    // TODO: Check credentials and login
                }

                CustomSpannableString(
                    SpannedString(
                        text = getString(R.string.dont_have_an_account),
                        fontWeight = FontWeights.FOUR_HUNDRED,
                        color = BlueText,
                        size = 13.sp
                    ),
                    SpannedString(
                        text = getString(R.string.sign_up),
                        fontWeight = FontWeights.FIVE_HUNDRED,
                        color = BaseGreen,
                        size = 13.sp,
                        onClick = {
                            navHostController.navigate(Authentication.SignUp.route) {
                                popUpTo(Authentication.SignInSignUp.route) {
                                    inclusive = false
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                )
            }

            GoogleOrAppleLoginBlock()
        }
    }
}

@Composable
fun GoogleOrAppleLoginBlock() {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .background(BlueText)
                    .weight(1f)
            )
            Text(
                text = getString(R.string.or_continue_with),
                fontWeight = getFontWeightFor(FontWeights.FOUR_HUNDRED),
                fontSize = 15.sp,
                lineHeight = 15.sp
            )
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .background(BlueText)
                    .weight(1f)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(19.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            BaseLeftImageButton(
                text = getString(R.string.google),
                image = R.drawable.ic_google_logo,
                modifier = Modifier.weight(1f)
            )
            BaseLeftImageButton(
                text = getString(R.string.apple),
                image = R.drawable.ic_apple_logo,
                modifier = Modifier.weight(1f)
            )
        }
    }
}