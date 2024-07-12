package com.testapp.mercaditoapk.remote

import com.testapp.mercaditoapk.model.Publication
import com.testapp.mercaditoapk.model.PublicationDTO
import retrofit2.Response
import retrofit2.http.*

interface ApiPublication {
    @GET("publications")
    suspend fun getAllPublications(): Response<List<Publication>>

    @GET("publications/{id}")
    suspend fun getPublicationById(@Path("id") id: Long): Response<Publication>

    @POST("publications")
    suspend fun createPublication(@Body publicationDTO: PublicationDTO): Response<String>

    @PUT("publications")
    suspend fun updatePublication(@Body publicationDTO: PublicationDTO): Response<String>

    @DELETE("publications/{id}")
    suspend fun deletePublication(@Path("id") id: Long): Response<String>

    @GET("publications/recent")
    suspend fun getRecentPublications(): Response<List<Publication>>

    @GET("publications/random-featured")
    suspend fun getRandomFeaturedPublications(): Response<List<Publication>>

    @GET("publications/random")
    suspend fun getRandomPublications(): Response<List<Publication>>
}

