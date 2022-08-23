package com.zavosh.jetpackpaggin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.zavosh.jetpackpaggin.data.Result
import com.zavosh.jetpackpaggin.network.ApiService
import com.zavosh.jetpackpaggin.network.RetrofitInstance
import com.zavosh.jetpackpaggin.paginssource.ResultPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class MainViewModel : ViewModel() {

    var apiService: ApiService =
        RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)


    fun getList(): Flow<PagingData<Result>> {
        return Pager(config = PagingConfig(pageSize = 42, maxSize = 200), pagingSourceFactory = {
            ResultPagingSource(apiService).apply {

            }
        }).flow.cachedIn(viewModelScope)
    }

}