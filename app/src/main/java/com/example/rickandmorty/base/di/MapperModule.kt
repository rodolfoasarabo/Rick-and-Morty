package com.example.rickandmorty.base.di

import com.example.rickandmorty.mapper.*
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    @Reusable
    fun bindsCharacterResponseMapper(
        characterResponseToModelMapperImpl: CharacterResponseToModelMapperImpl
    ): CharacterResponseToModelMapper

    @Binds
    @Reusable
    fun bindsEpisodeModelToEpisodeDetailUiModelMapper(
        episodeModelToEpisodeDetailUiModelMapperImpl: EpisodeModelToEpisodeDetailUiModelMapperImpl
    ): EpisodeModelToEpisodeDetailUiModelMapper

    @Binds
    @Reusable
    fun bindsEpisodeModelToUiModelMapper(
        episodeModelToUiModelMapperImpl: EpisodeModelToUiModelMapperImpl
    ): EpisodeModelToUiModelMapper

    @Binds
    @Reusable
    fun bindsEpisodeResponseToModelMapper(
        episodeResponseToModelMapperImpl: EpisodeResponseToModelMapperImpl
    ): EpisodeResponseToModelMapper
}