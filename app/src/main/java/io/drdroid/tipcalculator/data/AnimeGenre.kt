package io.drdroid.tipcalculator.data


import com.google.gson.annotations.SerializedName
import io.drdroid.tipcalculator.data.models.GenreModel

data class AnimeGenre(
    @SerializedName("data")
    val data: List<GenreModel>
)