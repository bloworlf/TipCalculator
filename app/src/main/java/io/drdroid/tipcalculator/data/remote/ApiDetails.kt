package io.drdroid.tipcalculator.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiDetails {

    private const val BASE_URL: String = "https://api.jikan.moe/"
    private const val API_VERSION: String = "v4"

    private const val API_URL = "${io.drdroid.tipcalculator.data.remote.ApiDetails.BASE_URL}${io.drdroid.tipcalculator.data.remote.ApiDetails.API_VERSION}/"

    const val RANDOM_ANIME = io.drdroid.tipcalculator.data.remote.ApiDetails.API_URL + "random/anime/"
    const val GENRE_ANIME = io.drdroid.tipcalculator.data.remote.ApiDetails.API_URL + "genres/anime"
    const val SEARCH_ANIME = io.drdroid.tipcalculator.data.remote.ApiDetails.API_URL + "anime"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(io.drdroid.tipcalculator.data.remote.ApiDetails.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(io.drdroid.tipcalculator.data.remote.ApiDetails.okHttpClient)
        .build()
    val apiClient: io.drdroid.tipcalculator.data.remote.ApiCall = io.drdroid.tipcalculator.data.remote.ApiDetails.retrofit.create(
        io.drdroid.tipcalculator.data.remote.ApiCall::class.java)
}