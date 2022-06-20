package com.example.rickandmorty.data.datasource

import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.data.model.EpisodeModel
import com.example.rickandmorty.data.model.GenericModel
import com.example.rickandmorty.data.model.InfoModel
import com.example.rickandmorty.mapper.EpisodeResponseToModelMapper
import javax.inject.Inject

interface EpisodeListDataSource {

    suspend fun getAllEpisodes(
        page: Int
    ): GenericModel<EpisodeModel>
}

class EpisodeListDataSourceImpl @Inject constructor(
    private val service: RickAndMortyApi,
    private val mapper: EpisodeResponseToModelMapper
) : EpisodeListDataSource {

    override suspend fun getAllEpisodes(
        page: Int
    ): GenericModel<EpisodeModel> {
        val response = service.getAllEpisodes(page)

        val infoModel = InfoModel(
            response.info.count,
            response.info.pages,
            response.info.next,
            response.info.prev
        )

        val episodeModel = response.results.map {
            mapper.mapFrom(it)
        }

        return GenericModel(
            infoModel,
            episodeModel
        )
    }
}