package com.adwera.mvvmkotlin.data.network.responses

import com.adwera.mvvmkotlin.data.database.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
) {
}