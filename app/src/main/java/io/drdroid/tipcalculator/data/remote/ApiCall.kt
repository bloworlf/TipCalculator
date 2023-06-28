package io.drdroid.tipcalculator.data.remote

import io.drdroid.tipcalculator.data.Anime
import io.drdroid.tipcalculator.data.AnimeGenre
import io.drdroid.tipcalculator.data.AnimeSearch
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {

    @GET(io.drdroid.tipcalculator.data.remote.ApiDetails.RANDOM_ANIME)
    suspend fun getRandomAnime(): io.drdroid.tipcalculator.data.Anime

    @GET(io.drdroid.tipcalculator.data.remote.ApiDetails.GENRE_ANIME)
    suspend fun getGenres(): io.drdroid.tipcalculator.data.AnimeGenre

    @GET(io.drdroid.tipcalculator.data.remote.ApiDetails.SEARCH_ANIME)
    suspend fun searchAnime(
        @Query("sfw") sfw: Boolean? = null,
        @Query("page") page: Int = 1,
        @Query("q") q: String = "",
    ): io.drdroid.tipcalculator.data.AnimeSearch
}