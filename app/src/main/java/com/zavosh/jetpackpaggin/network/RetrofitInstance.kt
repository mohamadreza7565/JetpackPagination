package com.zavosh.jetpackpaggin.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class RetrofitInstance {

    companion object {

        val baseUrl = "https://rickandmortyapi.com/api/"

        fun getRetrofitInstance(): Retrofit =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

}