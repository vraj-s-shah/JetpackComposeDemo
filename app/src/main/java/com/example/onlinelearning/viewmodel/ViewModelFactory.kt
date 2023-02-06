package com.example.onlinelearning.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onlinelearning.data.local.AppDatabase
import com.example.onlinelearning.utils.SharedPrefs

class ViewModelFactory private constructor(
    private val mApplication: Application,
    private val mSharedPrefs: SharedPrefs,
    private val mAppDatabase: AppDatabase
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
                SignInViewModel(
                    mAppDatabase.usersDao()
                )
            }
            isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(
                    mAppDatabase.usersDao()
                )
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
            INSTANCE ?: synchronized(Any()) {
                ViewModelFactory(
                    application,
                    SharedPrefs.getInstance(application),
                    AppDatabase.getInstance(application)
                ).also { INSTANCE = it }
            }
    }
}