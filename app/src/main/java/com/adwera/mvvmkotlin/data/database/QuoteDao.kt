package com.adwera.mvvmkotlin.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adwera.mvvmkotlin.data.database.entities.Quote

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllQuotes(quotes:List<Quote>)

    @Query("SELECT * FROM Quote ORDER BY id DESC")
    fun getAllQuotes() : LiveData<List<Quote>>

}