package com.testapp.mercaditoapk.remote

import com.testapp.mercaditoapk.model.Student
import com.testapp.mercaditoapk.model.StudentDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiStudent {

    @POST("/student/login")
    suspend fun login(
        @Query("cif") cif: Long,
        @Query("password") password: String
    ): Response<Student>

    @GET("/student/all")
    suspend fun getAllStudents(): Response<List<Student>>

    @GET("/student/find/{CIF}")
    suspend fun getStudentById(@Path("CIF") cif: Long): Response<Student>

    @POST("/student/create")
    suspend fun createStudent(@Body studentDTO: StudentDTO): Response<String>

    @DELETE("/student/delete/{CIF}")
    suspend fun deleteStudent(@Path("CIF") cif: Long): Response<String>

    @PUT("/student/update")
    suspend fun updateStudent(@Body studentDTO: StudentDTO): Response<String>

    @PUT("/student/{idFollowing}/following/{idStudent}")
    suspend fun assignFollowingToStudent(
        @Path("idFollowing") idFollowing: Long,
        @Path("idStudent") idFollower: Long
    ): Response<String>

    @PUT("/student/{idFollowing}/removeFollowing/{idStudent}")
    suspend fun removeFollowingFromStudent(
        @Path("idFollowing") idFollowing: Long,
        @Path("idStudent") idFollower: Long
    ): Response<String>
}



