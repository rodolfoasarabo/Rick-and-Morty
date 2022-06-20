package com.example.rickandmorty.usecase

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.repository.EpisodeListRepository
import com.example.rickandmorty.mapper.EpisodeModelToUiModelMapper
import com.example.rickandmorty.view.uimodel.EpisodeUiModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EpisodeSource @Inject constructor(
    private val episodeListRepository: EpisodeListRepository,
    private val mapper: EpisodeModelToUiModelMapper
) : PagingSource<Int, EpisodeUiModel>() {

    override fun getRefreshKey(
        state: PagingState<Int, EpisodeUiModel>
    ) = state.anchorPosition

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, EpisodeUiModel> {
        return try {
            val nextPage = params.key ?: 1

            val response = episodeListRepository.getAllEpisodes(page = nextPage)

            val nextPageNumber = if (response.info.next != null) {
                Uri.parse(response.info.next)?.getQueryParameter("page")?.toInt()
            } else null
            val previousPageNumber = if (response.info.prev != null) {
                Uri.parse(response.info.prev).getQueryParameter("page")?.toInt()
            } else null

            val episodes = response.results.map {
                mapper.mapFrom(it)
            }

            LoadResult.Page(
                data = episodes,
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