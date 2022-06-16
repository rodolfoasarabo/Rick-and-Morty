package com.example.rickandmorty.data.datasource

import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.mapper.EpisodeResponseToModelMapper
import com.example.rickandmorty.data.model.EpisodeModel
import javax.inject.Inject

interface EpisodeListDataSource {

    suspend fun getAllEpisodes(): List<EpisodeModel>
}

class EpisodeListDataSourceImpl @Inject constructor(
    private val service: RickAndMortyApi,
    private val mapper: EpisodeResponseToModelMapper
) : EpisodeListDataSource {

    override suspend fun getAllEpisodes(): List<EpisodeModel> {
        val response = service.getAllEpisodes()

        return response.results.map {
            mapper.mapFrom(it)
        }
    }
}