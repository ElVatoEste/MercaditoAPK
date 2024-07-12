package com.testapp.mercaditoapk.network

import com.testapp.mercaditoapk.remote.ApiPublication
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private const val BASE_URL = "http://localhost:8080"

    val publicacionesApi: ApiPublication by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiPublication::class.java)
    }
}
