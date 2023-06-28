package io.drdroid.tipcalculator.data.models


import com.google.gson.annotations.SerializedName

data class WebpModel(
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("large_image_url")
    val largeImageUrl: String?,
    @SerializedName("small_image_url")
    val smallImageUrl: String?
)