package io.drdroid.tipcalculator.data.models


import com.google.gson.annotations.SerializedName

data class TitleModel(
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
)