package com.example.rickandmorty.view.uimodel

data class EpisodeDetailUiModel(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<CharacterUiModel>
)

fun makeEpisodeUiModel() =
    EpisodeDetailUiModel(
        id = 1,
        name = "Pilot",
        airDate = "December 2, 2013",
        episode = "S01E01",
        characters = listOf(
            CharacterUiModel(
                1,
                "Rick Sanchez asdasdas",
                "Alive",
                "Human",
                "",
                "Male",
                CharacterOriginUiModel("Earth (C-137)", ""),
                CharacterLocationUiModel("Citadel of Ricks", ""),
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                listOf()
            )
        ),
    )