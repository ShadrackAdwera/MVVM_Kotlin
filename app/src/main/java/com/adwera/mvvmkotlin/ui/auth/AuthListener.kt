package com.adwera.mvvmkotlin.ui.auth

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message:String)
}