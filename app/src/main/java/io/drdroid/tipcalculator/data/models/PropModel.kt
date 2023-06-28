package io.drdroid.tipcalculator.data.models


import com.google.gson.annotations.SerializedName

data class PropModel(
    @SerializedName("from")
    val from: io.drdroid.tipcalculator.data.models.FromModel?,
    @SerializedName("string")
    val string: String?,
    @SerializedName("to")
    val to: io.drdroid.tipcalculator.data.models.ToModel?
)