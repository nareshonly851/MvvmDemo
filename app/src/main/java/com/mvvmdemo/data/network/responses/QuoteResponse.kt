package com.mvvmdemo.data.network.responses

import com.mvvmdemo.data.db.entities.Quote


data class QuoteResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)
