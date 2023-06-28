package io.drdroid.tipcalculator.data


import com.google.gson.annotations.SerializedName
import io.drdroid.tipcalculator.data.models.AnimeModel

data class Anime(
    @SerializedName("data")
    val data: AnimeModel?
)