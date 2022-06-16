package com.example.rickandmorty.base.di

import com.example.rickandmorty.data.datasource.EpisodeDetailDataSource
import com.example.rickandmorty.data.datasource.EpisodeDetailDataSourceImpl
import com.example.rickandmorty.data.datasource.EpisodeListDataSource
import com.example.rickandmorty.data.datasource.EpisodeListDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Reusable
    fun bindsEpisodeListDataSource(
        episodeListDataSourceImpl: EpisodeListDataSourceImpl
    ): EpisodeListDataSource

    @Binds
    @Reusable
    fun bindsEpisodeDetailDataSource(
        episodeDetailDataSourceImpl: EpisodeDetailDataSourceImpl
    ): EpisodeDetailDataSource
}