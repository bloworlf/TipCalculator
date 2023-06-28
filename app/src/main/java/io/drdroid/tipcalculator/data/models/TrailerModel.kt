package io.drdroid.tipcalculator.data.models


import com.google.gson.annotations.SerializedName

data class TrailerModel(
    @SerializedName("embed_url")
    val embedUrl: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("youtube_id")
    val youtubeId: String?
)