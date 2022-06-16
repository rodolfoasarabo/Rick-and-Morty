package com.example.rickandmorty.data.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterOriginModel,
    val location: CharacterLocationModel,
    val image: String,
    val episode: List<String>
)

data class CharacterOriginModel(
    val name: String,
    val url: String
)

data class CharacterLocationModel(
    val name: String,
    val url: String
)