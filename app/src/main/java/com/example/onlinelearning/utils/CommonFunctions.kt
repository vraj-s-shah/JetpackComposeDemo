package com.example.onlinelearning.utils

import android.content.Intent
import androidx.activity.ComponentActivity

fun ComponentActivity.startAndFinish(destination: Class<*>) {
    startActivity(Intent(baseContext, destination))
    finish()
}