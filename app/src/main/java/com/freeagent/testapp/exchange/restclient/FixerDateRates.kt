package com.freeagent.testapp.exchange.restclient

import com.squareup.moshi.Json

class FixerDateRates (
    val base: String,
    val date: String,
    val historical: Boolean,
    val rates: Map<String, Double>,
    val success: Boolean,
    @Json(name = "timestamp") val timeStamp: Int
)