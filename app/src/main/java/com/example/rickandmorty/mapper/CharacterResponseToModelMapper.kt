package com.example.rickandmorty.mapper

import com.example.rickandmorty.data.api.response.CharacterLocationResponse
import com.example.rickandmorty.data.api.response.CharacterOriginResponse
import com.example.rickandmorty.data.api.response.CharacterResponse
import com.example.rickandmorty.data.model.CharacterLocationModel
import com.example.rickandmorty.data.model.CharacterModel
import com.example.rickandmorty.data.model.CharacterOriginModel
import javax.inject.Inject

interface CharacterResponseToModelMapper {

    fun mapFrom(from: CharacterResponse): CharacterModel
}

class CharacterResponseToModelMapperImpl @Inject constructor() :
    CharacterResponseToModelMapper {

    override fun mapFrom(from: CharacterResponse) = CharacterModel(
        id = from.id,
        name = from.name,
        status = from.status,
        species = from.species,
        type = from.type,
        gender = from.gender,
        origin = mapOrigin(from.origin),
        location = mapLocation(from.location),
        image = from.image,
        episode = from.episode
    )

    private fun mapOrigin(from: CharacterOriginResponse) =
        CharacterOriginModel(
            name = from.name,
            url = from.url
        )

    private fun mapLocation(from: CharacterLocationResponse) =
        CharacterLocationModel(
            name = from.name,
            url = from.url
        )
}