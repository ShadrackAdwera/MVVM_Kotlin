package com.adwera.mvvmkotlin.ui.home.profile

import androidx.lifecycle.ViewModel
import com.adwera.mvvmkotlin.data.repositories.UserRepository

class ProfileViewModel (repository: UserRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    val user = repository.getUser()
}
