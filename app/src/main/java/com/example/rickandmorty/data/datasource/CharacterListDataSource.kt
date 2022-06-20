package com.example.rickandmorty.data.datasource

import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.data.model.CharacterModel
import com.example.rickandmorty.data.model.FilterModel
import com.example.rickandmorty.data.model.GenericModel
import com.example.rickandmorty.data.model.InfoModel
import com.example.rickandmorty.mapper.CharacterResponseToModelMapper
import javax.inject.Inject

interface CharacterListDataSource {

    suspend fun getAllCharacters(
        page: Int
    ): GenericModel<CharacterModel>
}

class CharacterListDataSourceImpl @Inject constructor(
    private val service: RickAndMortyApi,
    private val mapper: CharacterResponseToModelMapper
) : CharacterListDataSource {

    override suspend fun getAllCharacters(
        page: Int
    ): GenericModel<CharacterModel> {
        val response = service.getAllCharacters(page)

        val infoModel = InfoModel(
            response.info.count,
            response.info.pages,
            response.info.next,
            response.info.prev
        )

        val characterModel = response.results.map {
            mapper.mapFrom(it)
        }

        return GenericModel(
            infoModel,
            characterModel
        )
    }
}