package io.drdroid.tipcalculator.data.models


import com.google.gson.annotations.SerializedName

data class ExplicitGenreModel(
    @SerializedName("mal_id")
    val malId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)