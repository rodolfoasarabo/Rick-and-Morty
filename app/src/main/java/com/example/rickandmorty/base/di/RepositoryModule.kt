package com.example.rickandmorty.base.di

import com.example.rickandmorty.data.repository.EpisodeDetailRepository
import com.example.rickandmorty.data.repository.EpisodeDetailRepositoryImpl
import com.example.rickandmorty.EpisodeListRepository
import com.example.rickandmorty.EpisodeListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Reusable
    fun bindEpisodeListRepository(
        episodeListRepositoryImpl: EpisodeListRepositoryImpl
    ): EpisodeListRepository

    @Binds
    @Reusable
    fun bindEpisodeDetailRepository(
        episodeDetailRepositoryImpl: EpisodeDetailRepositoryImpl
    ): EpisodeDetailRepository

}