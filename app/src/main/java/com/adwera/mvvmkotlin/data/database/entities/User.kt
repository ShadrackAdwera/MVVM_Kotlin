package com.adwera.mvvmkotlin.data.database.entities

import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

data class User(
    var id : Int? = null,
    var name : String?=null,
    var email : String?=null,
    var email_verified_at : String?=null,
    var created_at : String?=null,
    var updated_at : String?=null
) {
    @PrimaryKey(autoGenerate = false)
    var uid : Int = CURRENT_USER_ID
}