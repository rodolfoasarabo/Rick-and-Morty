package com.example.rickandmorty.data.api.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterOriginResponse,
    val location: CharacterLocationResponse,
    val image: String,
    val episode: List<String>,
    val created: String,
    val url: String
)

@JsonClass(generateAdapter = true)
data class CharacterOriginResponse(
    val name: String,
    val url: String
)

@JsonClass(generateAdapter = true)
data class CharacterLocationResponse(
    val name: String,
    val url: String
)

fun makeCharacter() =
    CharacterResponse(
        1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = CharacterOriginResponse(
            name = "Earth",
            url = ""
        ),
        location = CharacterLocationResponse(
            name = "Earth",
            url = ""
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episode = emptyList(),
        url = "https://rickandmortyapi.com/api/character/1",
        created = "2017-11-04T18:48:46.250Z"
    )