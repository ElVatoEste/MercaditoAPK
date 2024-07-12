package com.testapp.mercaditoapk.remote

import com.testapp.mercaditoapk.model.ImageDTO
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiImage {

    @GET("/image/findStudentImage/{studentId}")
    suspend fun downloadStudentImage(@Path("studentId") studentId: Long): Response<ResponseBody>

    @GET("/image/findPublicationImage/{imageId}")
    suspend fun downloadPublicationImage(@Path("imageId") imageId: Long): Response<ResponseBody>

    @Multipart
    @POST("/image/{idPublication}/upload/{idStudent}")
    suspend fun uploadImage(
        @Part file: List<MultipartBody.Part>,
        @Path("idPublication") idPublication: Long,
        @Path("idStudent") idStudent: Long
    ): Response<String>

    @DELETE("/image/delete/{id}")
    suspend fun deleteImage(@Path("id") id: Long): Response<String>

    @GET("/image/getImagesIDs/{publicationId}")
    suspend fun getImagesIDs(@Path("publicationId") id: Long): Response<List<Long>>
}
