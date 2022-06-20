package com.example.rickandmorty.mapper

import com.example.rickandmorty.data.api.response.CharacterResponse
import com.example.rickandmorty.data.api.response.EpisodeResponse
import com.example.rickandmorty.data.model.EpisodeModel
import javax.inject.Inject

interface EpisodeResponseToModelMapper {

    fun mapFrom(
        from: EpisodeResponse,
        characterResponseList: List<CharacterResponse> = emptyList()
    ): EpisodeModel
}

class EpisodeResponseToModelMapperImpl @Inject constructor(
    private val characterResponseToModelMapper: CharacterResponseToModelMapper
) : EpisodeResponseToModelMapper {

    override fun mapFrom(
        from: EpisodeResponse,
        characterResponseList: List<CharacterResponse>
    ): EpisodeModel {
        return EpisodeModel(
            from.id,
            from.name,
            from.airDate,
            from.episode,
            characterResponseList.map {
                characterResponseToModelMapper.mapFrom(it)
            }
        )
    }
}