package com.example.rickandmorty.view.uimodel

data class CharacterUiModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterOriginUiModel,
    val location: CharacterLocationUiModel,
    val image: String,
    val episode: List<String>
)

data class CharacterOriginUiModel(
    val name: String,
    val url: String
)

data class CharacterLocationUiModel(
    val name: String,
    val url: String
)