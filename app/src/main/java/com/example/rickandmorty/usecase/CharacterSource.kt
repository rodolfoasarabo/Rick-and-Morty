package com.example.rickandmorty.usecase

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.repository.CharacterListRepository
import com.example.rickandmorty.mapper.CharacterModelToUiModelMapper
import com.example.rickandmorty.view.uimodel.CharacterUiModel
import retrofit2.HttpException
import java.io.IOException

class CharacterSource constructor(
    private val characterListRepository: CharacterListRepository,
    private val mapper: CharacterModelToUiModelMapper
) : PagingSource<Int, CharacterUiModel>() {

    override fun getRefreshKey(
        state: PagingState<Int, CharacterUiModel>
    ) = 1

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, CharacterUiModel> {
        return try {
            val nextPage = params.key ?: 1

            val response =
                characterListRepository.getAllCharacters(page = nextPage)

            val nextPageNumber = if (response.info.next != null) {
                Uri.parse(response.info.next)?.getQueryParameter("page")?.toInt()
            } else null
            val previousPageNumber = if (response.info.prev != null) {
                Uri.parse(response.info.prev).getQueryParameter("page")?.toInt()
            } else null

            val characters = response.results.map {
                mapper.mapFrom(it)
            }

            LoadResult.Page(
                characters,
                prevKey = previousPageNumber,
                nextKey = nextPageNumber
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}