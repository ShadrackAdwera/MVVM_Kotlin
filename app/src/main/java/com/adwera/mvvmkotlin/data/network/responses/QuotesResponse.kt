package com.adwera.mvvmkotlin.data.network.responses

import com.adwera.mvvmkotlin.data.database.entities.Quote

data class QuotesResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)