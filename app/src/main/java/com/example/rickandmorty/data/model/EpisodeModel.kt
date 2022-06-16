package com.example.rickandmorty.data.model

data class EpisodeModel(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<CharacterModel> = emptyList()
)