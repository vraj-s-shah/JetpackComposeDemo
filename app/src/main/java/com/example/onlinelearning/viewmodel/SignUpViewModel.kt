package com.example.onlinelearning.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel : ViewModel() {

    private val mName = MutableStateFlow("")
    val name = mName.asStateFlow()

    private val mEmail = MutableStateFlow("")
    val email = mEmail.asStateFlow()

    private val mPassword = MutableStateFlow("")
    val password = mPassword.asStateFlow()

    private val mIsPasswordHidden = MutableStateFlow(true)
    val isPasswordHidden = mIsPasswordHidden.asStateFlow()

    fun setName(name: String) {
        mName.value = name
    }

    fun setEmail(email: String) {
        mEmail.value = email
    }

    fun setPassword(password: String) {
        mPassword.value = password
    }

    fun setIsPasswordHidden(isHidden: Boolean) {
        mIsPasswordHidden.value = isHidden
    }
}