package com.zavosh.jetpackpaggin.network

import com.zavosh.jetpackpaggin.data.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
interface ApiService {

    @GET("character")
    suspend fun getDataFromApi(@Query("page") query: Int) : Response

}