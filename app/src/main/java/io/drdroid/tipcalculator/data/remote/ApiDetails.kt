package io.drdroid.tipcalculator.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiDetails {

    private const val BASE_URL: String = "https://api.jikan.moe/"
    private const val API_VERSION: String = "v4"

    private const val API_URL = "${BASE_URL}${API_VERSION}/"

    const val RANDOM_ANIME = API_URL + "random/anime/"
    const val GENRE_ANIME = API_URL + "genres/anime"
    const val SEARCH_ANIME = API_URL + "anime"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    val apiClient: ApiCall = retrofit.create(
        ApiCall::class.java)
}