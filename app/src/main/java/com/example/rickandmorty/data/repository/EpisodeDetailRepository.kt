package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.datasource.EpisodeDetailDataSource
import com.example.rickandmorty.data.model.EpisodeModel
import javax.inject.Inject

interface EpisodeDetailRepository {

    suspend fun getEpisodeById(episodeId: String): EpisodeModel
}

class EpisodeDetailRepositoryImpl @Inject constructor(
    private val episodeDetailDataSource: EpisodeDetailDataSource
) : EpisodeDetailRepository {

    override suspend fun getEpisodeById(episodeId: String) =
        episodeDetailDataSource.getEpisodeById(episodeId)
}