package io.drdroid.tipcalculator.data.models


import com.google.gson.annotations.SerializedName

data class FromModel(
    @SerializedName("day")
    val day: Int?,
    @SerializedName("month")
    val month: Int?,
    @SerializedName("year")
    val year: Int?
)