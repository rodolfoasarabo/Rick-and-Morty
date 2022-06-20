package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.api.response.CharacterResponse
import com.example.rickandmorty.data.api.response.EpisodeResponse
import com.example.rickandmorty.data.api.response.GenericResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character/{ids}")
    suspend fun getMultipleCharacters(@Path("ids") ids: List<String>): List<CharacterResponse>

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
    ): GenericResponse<CharacterResponse>

    @GET("episode")
    suspend fun getAllEpisodes(
        @Query("page") page: Int
    ): GenericResponse<EpisodeResponse>

    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: String): EpisodeResponse

}