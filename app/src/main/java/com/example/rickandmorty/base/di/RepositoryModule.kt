package com.example.rickandmorty.base.di

import com.example.rickandmorty.data.repository.*
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

    @Binds
    @Reusable
    fun bindCharacterListRepository(
        characterListRepositoryImpl: CharacterListRepositoryImpl
    ): CharacterListRepository
}