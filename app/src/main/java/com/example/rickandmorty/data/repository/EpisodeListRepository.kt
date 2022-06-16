package com.example.rickandmorty

import com.example.rickandmorty.data.datasource.EpisodeListDataSource
import com.example.rickandmorty.data.model.EpisodeModel
import javax.inject.Inject

interface EpisodeListRepository {
    suspend fun getAllEpisodes(): List<EpisodeModel>
}

class EpisodeListRepositoryImpl @Inject constructor(
    private val episodeListDataSource: EpisodeListDataSource
) : EpisodeListRepository {

    override suspend fun getAllEpisodes() = episodeListDataSource.getAllEpisodes()
}