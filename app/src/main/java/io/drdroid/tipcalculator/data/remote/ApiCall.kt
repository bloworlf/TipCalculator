package io.drdroid.tipcalculator.data.remote

import io.drdroid.tipcalculator.data.Anime
import io.drdroid.tipcalculator.data.AnimeGenre
import io.drdroid.tipcalculator.data.AnimeSearch
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {

    @GET(ApiDetails.RANDOM_ANIME)
    suspend fun getRandomAnime(): Anime

    @GET(ApiDetails.GENRE_ANIME)
    suspend fun getGenres(): AnimeGenre

    @GET(ApiDetails.SEARCH_ANIME)
    suspend fun searchAnime(
        @Query("sfw") sfw: Boolean? = null,
        @Query("page") page: Int = 1,
        @Query("q") q: String = "",
    ): AnimeSearch
}