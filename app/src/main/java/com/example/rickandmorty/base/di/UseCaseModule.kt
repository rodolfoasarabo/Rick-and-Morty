package com.example.rickandmorty.base.di

import com.example.rickandmorty.usecase.GetAllEpisodes
import com.example.rickandmorty.usecase.GetAllEpisodesUseCase
import com.example.rickandmorty.usecase.GetEpisodeById
import com.example.rickandmorty.usecase.GetEpisodeByIdUseCase
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    @Reusable
    fun bindGetAllEpisodesUseCase(
        getAllEpisodesUseCase: GetAllEpisodesUseCase
    ): GetAllEpisodes

    @Binds
    @Reusable
    fun bindGetEpisodeByIdUseCase(
        getEpisodeByIdUseCase: GetEpisodeByIdUseCase
    ): GetEpisodeById
}