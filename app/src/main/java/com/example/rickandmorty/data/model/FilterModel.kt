package com.example.rickandmorty.data.model

data class FilterModel(
    val name: String? = null,
    val status: CharacterStatus? = null,
    val gender: CharacterGender? = null
)

enum class CharacterStatus(val value: String) {
    SELECT("Select"),
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("Unknown")
}

enum class CharacterGender(val value: String) {
    SELECT("Select"),
    MALE("Male"),
    FEMALE("Female"),
    GENDERLESS("Genderless"),
    UNKNOWN("Unknown")
}