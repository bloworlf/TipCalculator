package io.drdroid.tipcalculator.data.models


import com.google.gson.annotations.SerializedName

data class AnimeModel(
    @SerializedName("aired")
    val aired: AiredModel?,
    @SerializedName("airing")
    val airing: Boolean?,
    @SerializedName("approved")
    val approved: Boolean?,
    @SerializedName("background")
    val background: String?,
    @SerializedName("broadcast")
    val broadcast: BroadcastModel?,
    @SerializedName("demographics")
    val demographics: List<DemographicModel?>?,
    @SerializedName("duration")
    val duration: String?,
    @SerializedName("episodes")
    val episodes: Int?,
    @SerializedName("explicit_genres")
    val explicitGenres: List<ExplicitGenreModel?>?,
    @SerializedName("favorites")
    val favorites: Int?,
    @SerializedName("genres")
    val genres: List<GenreModel?>?,
    @SerializedName("images")
    val images: ImagesModel?,
    @SerializedName("licensors")
    val licensors: List<LicensorModel?>?,
    @SerializedName("mal_id")
    val malId: Int?,
    @SerializedName("members")
    val members: Int?,
    @SerializedName("popularity")
    val popularity: Int?,
    @SerializedName("producers")
    val producers: List<ProducerModel?>?,
    @SerializedName("rank")
    val rank: Int?,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("score")
    val score: Double?,
    @SerializedName("scored_by")
    val scoredBy: Double?,
    @SerializedName("season")
    val season: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("studios")
    val studios: List<StudioModel?>?,
    @SerializedName("synopsis")
    val synopsis: String?,
    @SerializedName("themes")
    val themes: List<ThemeModel?>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("title_english")
    val titleEnglish: String?,
    @SerializedName("title_japanese")
    val titleJapanese: String?,
    @SerializedName("title_synonyms")
    val titleSynonyms: List<String?>?,
    @SerializedName("titles")
    val titles: List<TitleModel?>?,
    @SerializedName("trailer")
    val trailer: TrailerModel?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("year")
    val year: Int?
)