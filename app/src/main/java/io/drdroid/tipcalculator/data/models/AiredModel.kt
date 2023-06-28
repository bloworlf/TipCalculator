package io.drdroid.tipcalculator.data.models


import com.google.gson.annotations.SerializedName

data class AiredModel(
    @SerializedName("from")
    val from: String?,
    @SerializedName("prop")
    val prop: PropModel?,
    @SerializedName("to")
    val to: String?
)