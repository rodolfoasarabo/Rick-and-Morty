package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.datasource.CharacterListDataSource
import com.example.rickandmorty.data.model.CharacterModel
import com.example.rickandmorty.data.model.FilterModel
import com.example.rickandmorty.data.model.GenericModel
import javax.inject.Inject

interface CharacterListRepository {

    suspend fun getAllCharacters(
        page: Int
    ): GenericModel<CharacterModel>
}

class CharacterListRepositoryImpl @Inject constructor(
    private val characterListDataSource: CharacterListDataSource
) : CharacterListRepository {

    override suspend fun getAllCharacters(page: Int) =
        characterListDataSource.getAllCharacters(page)
}