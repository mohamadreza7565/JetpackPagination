package com.zavosh.jetpackpaggin.paginssource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zavosh.jetpackpaggin.data.Result
import com.zavosh.jetpackpaggin.network.ApiService
import kotlinx.coroutines.flow.callbackFlow

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class ResultPagingSource(val apiService: ApiService) :
    PagingSource<Int, Result>() {

    companion object {
        private const val FIRST_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Result>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> =
        try {

            val nextPage: Int = params.key ?: FIRST_PAGE
            val response = apiService.getDataFromApi(nextPage)
            var nextPageNumber: Int? = null
            if (response.info?.next != null) {
                val uri = Uri.parse(response.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            LoadResult.Page(data = response.results, prevKey = null, nextKey = nextPageNumber)

        } catch (e: Exception) {
            LoadResult.Error(e)
        }

}