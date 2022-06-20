package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.datasource.EpisodeListDataSource
import com.example.rickandmorty.data.model.EpisodeModel
import com.example.rickandmorty.data.model.GenericModel
import javax.inject.Inject

interface EpisodeListRepository {
    suspend fun getAllEpisodes(
        page: Int
    ): GenericModel<EpisodeModel>
}

class EpisodeListRepositoryImpl @Inject constructor(
    private val episodeListDataSource: EpisodeListDataSource
) : EpisodeListRepository {

    override suspend fun getAllEpisodes(
        page: Int
    ) = episodeListDataSource.getAllEpisodes(page)
}