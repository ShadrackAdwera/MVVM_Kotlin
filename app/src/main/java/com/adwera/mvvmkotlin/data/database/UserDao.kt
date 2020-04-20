package com.adwera.mvvmkotlin.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adwera.mvvmkotlin.data.database.entities.CURRENT_USER_ID
import com.adwera.mvvmkotlin.data.database.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user: User) : Long

    @Query("SELECT * FROM User WHERE uid = $CURRENT_USER_ID")
    fun getUser() : LiveData<User>
}