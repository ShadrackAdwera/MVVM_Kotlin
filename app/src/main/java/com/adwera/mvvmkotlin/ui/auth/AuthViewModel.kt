package com.adwera.mvvmkotlin.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.adwera.mvvmkotlin.data.repositories.UserRepository

class AuthViewModel : ViewModel() {
    var email : String? = null
    var password: String? = null

    var authListener : AuthListener? = null

    fun onLoginClicked (view: View) {
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid Email/Password")
            return
        }
        val loginResponse = UserRepository().userLogin(email!!,password!!)
        authListener?.onSuccess(loginResponse)
    }
}