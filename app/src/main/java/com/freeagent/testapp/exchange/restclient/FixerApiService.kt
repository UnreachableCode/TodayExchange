package com.freeagent.testapp.exchange.restclient

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://api.apilayer.com/fixer/"
private const val API_KEY = "pdsFCtdMZzJKxYGFVeztEONsKTb8QjWg"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface FixerApiService {
    @Headers("apikey: $API_KEY")
    @GET("timeseries")
    suspend fun getFollowingRatesForTimeseries(): String
}

object FixerApi {
    val retroFitService : FixerApiService by lazy {
        retrofit.create(FixerApiService::class.java) }
}