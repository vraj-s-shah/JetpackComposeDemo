package com.example.onlinelearning.viewmodel

import android.app.Activity
import android.app.Application
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
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

@Composable
inline fun <reified T : ViewModel> Activity.obtainViewModel(): T =
    viewModel(factory = ViewModelFactory.getInstance(this.application))