package com.example.onlinelearning.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinelearning.data.local.dao.UsersDao
import com.example.onlinelearning.data.local.repository.UserRepository
import com.example.onlinelearning.utils.CredentialsValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(mUsersDao: UsersDao) : ViewModel() {

    private val mUserRepository = UserRepository(mUsersDao)

    private val mName = MutableStateFlow("")
    val name = mName.asStateFlow()

    private val mPassword = MutableStateFlow("")
    val password = mPassword.asStateFlow()

    private val mIsPasswordHidden = MutableStateFlow(true)
    val isPasswordHidden = mIsPasswordHidden.asStateFlow()

    fun setName(name: String) {
        mName.value = name.trim()
    }

    fun setPassword(password: String) {
        mPassword.value = password.trim()
    }

    fun setIsPasswordHidden(isHidden: Boolean) {
        mIsPasswordHidden.value = isHidden
    }

    fun validateUserCredentials(onCompletion: (CredentialsValidator) -> Unit) {
        viewModelScope.launch {
            onCompletion(
                when {
                    isAnyCredentialEmpty() -> CredentialsValidator.EmptyCredentials
                    else -> {
                        mUserRepository.getUser(name.value)?.let {
                            if (password.value != it.password) {
                                CredentialsValidator.WrongPassword
                            } else {
                                CredentialsValidator.ValidCredentials(it.id)
                            }
                        } ?: CredentialsValidator.UserNotFound
                    }
                }
            )
        }
    }

    private fun isAnyCredentialEmpty(): Boolean =
        name.value.isEmpty() || password.value.isEmpty()
}