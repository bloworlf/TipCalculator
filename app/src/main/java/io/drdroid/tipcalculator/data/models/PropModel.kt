package io.drdroid.tipcalculator.data.models


import com.google.gson.annotations.SerializedName

data class PropModel(
    @SerializedName("from")
    val from: FromModel?,
    @SerializedName("string")
    val string: String?,
    @SerializedName("to")
    val to: ToModel?
)