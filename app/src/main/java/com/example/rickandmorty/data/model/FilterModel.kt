package com.example.rickandmorty.data.model

data class FilterModel(
    val name: String? = null,
    val status: CharacterStatus? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: CharacterGender? = null
)

enum class CharacterStatus(val value: String) {
    ALIVE("alive"),
    DEAD("dead"),
    UNKNOWN("unknown")
}

enum class CharacterGender(val value: String) {
    MALE("male"),
    FEMALE("female"),
    GENDERLESS("genderless"),
    UNKNOWN("unknown")
}