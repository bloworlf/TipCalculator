package io.drdroid.tipcalculator.data.models


import com.google.gson.annotations.SerializedName

data class ImagesModel(
    @SerializedName("jpg")
    val jpg: JpgModel?,
    @SerializedName("webp")
    val webp: WebpModel?
)