package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.domain.PictureOfDay
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = Constants.BASE_URL

private const val  API_KEY = Constants.API_KEY

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(@Query("api_key") api_key: String = API_KEY, @Query("start_date") startDate: String): String

    @GET("planetary/apod")
    suspend fun getImageOfTheDay(@Query("api_key") api_key: String = API_KEY): PictureOfDay
}


object Api {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
}