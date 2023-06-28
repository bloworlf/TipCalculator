package io.drdroid.tipcalculator.data.models


import com.google.gson.annotations.SerializedName

data class PaginationModel(
    @SerializedName("has_next_page")
    val hasNextPage: Boolean,
    @SerializedName("items")
    val items: ItemsModel?,
    @SerializedName("last_visible_page")
    val lastVisiblePage: Int
)