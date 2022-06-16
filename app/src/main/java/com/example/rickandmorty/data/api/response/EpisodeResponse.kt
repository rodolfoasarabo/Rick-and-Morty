package com.example.rickandmorty.data.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeResponse(
    val id: Int,
    val name: String,
    @Json(name = "air_date") val airDate: String,
    val episode: String,
    val characters: List<String> = emptyList(),
    val url: String,
    val created: String
)

fun makeEpisode(name: String = "Pilot") =
    EpisodeResponse(
        id = 1,
        name = name,
        airDate = "December 2, 2013",
        episode = "S01E01",
        characters = emptyList(),
        url = "",
        created = ""
    )