package com.adwera.mvvmkotlin.ui.auth

import androidx.lifecycle.LiveData
import com.adwera.mvvmkotlin.data.database.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message:String)
}