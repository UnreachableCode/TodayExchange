package com.freeagent.testapp.exchange.restclient

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.*

private const val BASE_URL = "https://api.apilayer.com/fixer/"
private const val API_KEY = "pdsFCtdMZzJKxYGFVeztEONsKTb8QjWg"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface FixerApiService {
    @Headers("apikey: $API_KEY")
    @GET("timeseries")
    suspend fun getFollowingRatesForTimeseries(@Query("start_date") start: String,
                                               @Query("end_date") end: String,
                                               @Query("base") base: String,
                                               @Query("symbols") symbols: String,): String
}

object FixerApi {
    val retroFitService : FixerApiService by lazy {
        retrofit.create(FixerApiService::class.java) }
}