package com.freeagent.testapp.exchange.restclient

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

private const val BASE_URL = "https://api.apilayer.com/"
private const val API_KEY = "pdsFCtdMZzJKxYGFVeztEONsKTb8QjWg"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val jsonAdapter: JsonAdapter<DayRates> = moshi.adapter(DayRates::class.java)

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/*
Had really intended to use this one call, one time for a 5 day timespan and then do everything else offline.
But as it's my first time using Moshi, I couldn't quite figure out how to use the adapters to transform
the JSON from this call into usable objects. So I had to u-turn on this and use the date endpoint instead.
*/
interface FixerApiService {
    @Headers("apikey: $API_KEY")
    @GET("fixer/timeseries")
    suspend fun getFollowingRatesForTimeseries(@Query("start_date") start: String,
                                               @Query("end_date") end: String,
                                               @Query("base") base: String,
                                               @Query("symbols") symbols: String,): FixerTimeSeries

    @Headers("apikey: $API_KEY")
    @GET("fixer/{date}")
    suspend fun getFollowingRatesForDate(@Path("date") date: String,
                                         @Query("base") base: String,
                                         @Query("symbols") symbols: String,): FixerDateRates
}

object FixerApi {
    val retroFitService : FixerApiService by lazy {
        retrofit.create(FixerApiService::class.java) }
}