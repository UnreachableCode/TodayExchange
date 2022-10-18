package com.freeagent.testapp.exchange.restclient

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.ToJson

data class FixerTimeSeries(
    val base: String,
    @Json(name = "end_date") val endDate: String,
    val rates: DayRates,
    @Json(name = "start_date") val start_date: String,
    val success: Boolean,
    @Json(name = "timeseries") val timeSeries: Boolean
)

data class DayRates(
    private val rates: Map<String, Any>
)

data class DayRatesJson(
    val dayRate1: DayRate,
    val dayRate2: DayRate,
    val dayRate3: DayRate,
    val dayRate4: DayRate,
    val dayRate5: DayRate,
)

class DayRatesJsonAdapter {
    @FromJson
    fun fromJson(dayRatesJson: DayRatesJson): DayRates {
        println(dayRatesJson)
        return DayRates(
            rates = mapOf()
        )
    }
    @ToJson
    fun toJson(dayRate: DayRate): Any {
        throw NotImplementedError()
    }
}


data class DayRate(
    val date: String,
    private val rates: Map<String, Double>
)

