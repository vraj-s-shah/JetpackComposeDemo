package com.example.onlinelearning.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onlinelearning.utils.SharedPrefs

class ViewModelFactory private constructor(
    private val mApplication: Application,
    private val mSharedPrefs: SharedPrefs
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(OnboardingViewModel::class.java) -> {
                OnboardingViewModel(
                    mApplication,
                    mSharedPrefs
                )
            }
            isAssignableFrom(SplashScreenViewModel::class.java) -> {
                SplashScreenViewModel(
                    mSharedPrefs
                )
            }
            isAssignableFrom(SignInViewModel::class.java) -> {
                SignInViewModel()
            }
            isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel()
            }
            else -> {
                throw IllegalArgumentException(
                    "Unknown ViewModel class: ${modelClass.name}"
                )
            }
        }
    } as T

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(
                    application,
                    SharedPrefs(application)
                ).also { INSTANCE = it }
            }
    }
}