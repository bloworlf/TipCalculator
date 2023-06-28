package io.drdroid.tipcalculator.data.models


import com.google.gson.annotations.SerializedName

data class ImagesModel(
    @SerializedName("jpg")
    val jpg: io.drdroid.tipcalculator.data.models.JpgModel?,
    @SerializedName("webp")
    val webp: io.drdroid.tipcalculator.data.models.WebpModel?
)