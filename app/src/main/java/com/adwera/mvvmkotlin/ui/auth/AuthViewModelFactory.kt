package com.adwera.mvvmkotlin.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adwera.mvvmkotlin.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(userRepository) as T
    }
}