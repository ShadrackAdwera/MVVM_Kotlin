package com.adwera.mvvmkotlin.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adwera.mvvmkotlin.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class ProfileModelFactory(private val userRepository: UserRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(userRepository) as T
    }
}
