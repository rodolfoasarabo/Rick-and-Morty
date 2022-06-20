package com.example.rickandmorty.data.model

data class GenericModel<T>(
    val info: InfoModel,
    val results: List<T>
)

data class EpisodeModel(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<CharacterModel> = emptyList()
)

data class InfoModel(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)