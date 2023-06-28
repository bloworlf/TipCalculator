package io.drdroid.tipcalculator.data


import com.google.gson.annotations.SerializedName
import io.drdroid.tipcalculator.data.models.AnimeModel
import io.drdroid.tipcalculator.data.models.PaginationModel

data class AnimeSearch(
    @SerializedName("data")
    val data: List<AnimeModel>,
    @SerializedName("pagination")
    val pagination: PaginationModel
)