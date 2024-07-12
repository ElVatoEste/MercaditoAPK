package com.testapp.mercaditoapk.remote

import com.testapp.mercaditoapk.model.Publication
import com.testapp.mercaditoapk.model.PublicationDTO
import retrofit2.Response
import retrofit2.http.*

interface ApiPublication {
    @GET("/publication/all")
    suspend fun getAllPublications(): Response<List<Publication>>

    @GET("/publication/find/{id}")
    suspend fun getPublicationById(@Path("id") id: Long): Response<Publication>

    @POST("/publication/create")
    suspend fun createPublication(@Body publicationDTO: PublicationDTO): Response<String>

    @PUT("/publication/update")
    suspend fun updatePublication(@Body publicationDTO: PublicationDTO): Response<String>

    @DELETE("/publication/delete/{id}")
    suspend fun deletePublication(@Path("id") id: Long): Response<String>

    @GET("/publication/getMostRecentPublications")
    suspend fun getRecentPublications(): Response<List<Publication>>

    @GET("/publication/getMostRecentPublicationsId")
    suspend fun getRecentPublicationsId(): Response<List<Long>>

    @GET("/publication/getRandomFeaturedPublications")
    suspend fun getRandomFeaturedPublications(): Response<List<Publication>>

    @GET("publication/getRandomPublications")
    suspend fun getRandomPublications(): Response<List<Publication>>
}

