package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.api.response.CharacterResponse
import com.example.rickandmorty.data.api.response.EpisodeResponse
import com.example.rickandmorty.data.api.response.GenericResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {

    @GET("character/{ids}")
    suspend fun getMultipleCharacters(@Path("ids") ids: List<String>): List<CharacterResponse>

    @GET("episode")
    suspend fun getAllEpisodes(): GenericResponse<EpisodeResponse>

    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: String): EpisodeResponse

}