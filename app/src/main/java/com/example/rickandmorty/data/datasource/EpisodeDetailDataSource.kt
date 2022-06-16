package com.example.rickandmorty.data.datasource

import android.net.Uri
import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.data.model.EpisodeModel
import com.example.rickandmorty.mapper.EpisodeResponseToModelMapper
import javax.inject.Inject

interface EpisodeDetailDataSource {

    suspend fun getEpisodeById(episodeId: String): EpisodeModel
}

class EpisodeDetailDataSourceImpl @Inject constructor(
    private val service: RickAndMortyApi,
    private val episodeResponseToModelMapper: EpisodeResponseToModelMapper
) : EpisodeDetailDataSource {

    override suspend fun getEpisodeById(episodeId: String): EpisodeModel {
        val episode = service.getEpisodeById(episodeId)

        val characterIdList = episode.characters.map {
            Uri.parse(it).lastPathSegment.orEmpty()
        }

        val characters = service.getMultipleCharacters(characterIdList)

        return episodeResponseToModelMapper.mapFrom(episode, characters)
    }
}