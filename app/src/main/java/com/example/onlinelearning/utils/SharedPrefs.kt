package com.example.onlinelearning.utils

import android.annotation.SuppressLint
import android.content.Context

class SharedPrefs(private val context: Context) {

    private val lazySharedPrefs = lazy {
        context.getSharedPreferences("OnlineLearning", Context.MODE_PRIVATE)
    }
    private var mIsUserLoggedIn by BooleanPreference(
        lazySharedPrefs,
        "isUserLoggedIn",
        false
    )
    private var mIsOnboardingCompleted by BooleanPreference(
        lazySharedPrefs,
        "isOnboardingCompleted",
        false
    )

    var isUserLoggedIn: Boolean
        get() = mIsUserLoggedIn
        set(value) { mIsUserLoggedIn = value }

    var isOnboardingCompleted: Boolean
        get() = mIsOnboardingCompleted
        set(value) { mIsOnboardingCompleted = value }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: SharedPrefs? = null

        fun getInstance(context: Context): SharedPrefs =
            INSTANCE ?: synchronized(Any()) { SharedPrefs(context) }
    }
}