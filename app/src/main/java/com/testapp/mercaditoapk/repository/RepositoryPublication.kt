package com.testapp.mercaditoapk.repository

import android.util.Log
import com.testapp.mercaditoapk.model.Publication
import com.testapp.mercaditoapk.model.PublicationDTO
import com.testapp.mercaditoapk.remote.ApiAdapter
import com.testapp.mercaditoapk.remote.ApiPublication
import retrofit2.Response

class RepositoryPublication {

    private val apiPublication: ApiPublication by lazy {
        ApiAdapter.getInstance().create(ApiPublication::class.java)
    }

    suspend fun getAllPublications(): Result<List<Publication>> {
        return try {
            val response: Response<List<Publication>> = apiPublication.getAllPublications()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch publications"))
            }
        } catch (e: Exception) {
            Log.d("error", e.message ?: "Unknown error")
            Result.failure(e)
        }
    }

    suspend fun getPublicationById(id: Long): Result<Publication?> {
        return try {
            val response: Response<Publication> = apiPublication.getPublicationById(id)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("Failed to fetch publication by ID"))
            }
        } catch (e: Exception) {
            Log.d("error", e.message ?: "Unknown error")
            Result.failure(e)
        }
    }

    suspend fun getRecentPublications(): Result<List<Publication>> {
        return try {
            val response: Response<List<Publication>> = apiPublication.getRecentPublications()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch publication by ID"))
            }
        } catch (e: Exception) {
            Log.d("error", e.message ?: "Unknown error")
            Result.failure(e)
        }
    }

    suspend fun getRandomFeaturedPublications(): Result<List<Publication>> {
        return try {
            val response: Response<List<Publication>> = apiPublication.getRandomFeaturedPublications()
            if (response.isSuccessful) {
                val publications : List<Publication>? = response.body()
                val mutableList = publications?.toMutableList()
                mutableList?.shuffle()
                val shuffledList: List<Publication> = mutableList!!.toList()
                val finalResponse : Response<List<Publication>> = Response.success(shuffledList.take(6))
                Result.success(finalResponse.body() ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch publication by ID"))
            }
        } catch (e: Exception) {
            Log.d("error", e.message ?: "Unknown error")
            Result.failure(e)
        }
    }

    suspend fun getRandomPublications(): Result<List<Publication>> {
        return try {
            val response: Response<List<Publication>> = apiPublication.getRandomPublications()
            if (response.isSuccessful) {
                val publications : List<Publication>? = response.body()
                val mutableList = publications?.toMutableList()
                mutableList?.shuffle()
                val shuffledList: List<Publication> = mutableList!!.toList()
                val finalResponse : Response<List<Publication>> = Response.success(shuffledList.take(6))
                Result.success(finalResponse.body() ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch publication by ID"))
            }
        } catch (e: Exception) {
            Log.d("error", e.message ?: "Unknown error")
            Result.failure(e)
        }
    }

    suspend fun createPublication(publicationDTO: PublicationDTO): Result<String> {
        Log.d("Publication repository", "${publicationDTO.id}")

        return try {
            val response: Response<String> = apiPublication.createPublication(publicationDTO)
            if (response.isSuccessful) {
                Result.success(response.body() ?: "Publication created successfully")
            } else {
                Result.failure(Exception("Failed to create publication"))
            }
        } catch (e: Exception) {
            Log.d("error", e.message ?: "Unknown error")
            Result.failure(e)
        }
    }

    suspend fun updatePublication(publicationDTO: PublicationDTO): Result<String> {
        return try {
            val response: Response<String> = apiPublication.updatePublication(publicationDTO)
            if (response.isSuccessful) {
                Result.success(response.body() ?: "Publication updated successfully")
            } else {
                Result.failure(Exception("Failed to update publication"))
            }
        } catch (e: Exception) {
            Log.d("error", e.message ?: "Unknown error")
            Result.failure(e)
        }
    }

    suspend fun deletePublication(id: Long): Result<String> {
        return try {
            val response: Response<String> = apiPublication.deletePublication(id)
            if (response.isSuccessful) {
                Result.success(response.body() ?: "Publication deleted successfully")
            } else {
                Result.failure(Exception("Failed to delete publication"))
            }
        } catch (e: Exception) {
            Log.d("error", e.message ?: "Unknown error")
            Result.failure(e)
        }
    }
}
