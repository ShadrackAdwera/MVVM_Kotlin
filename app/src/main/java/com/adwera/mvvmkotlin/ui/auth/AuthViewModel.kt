package com.adwera.mvvmkotlin.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    var email : String? = null
    var password: String? = null

    fun onLoginClicked (view: View) {
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            //error message
            return
        }
        //successful login
    }
}