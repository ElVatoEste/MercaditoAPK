package com.testapp.mercaditoapk.repository

import android.util.Log
import com.testapp.mercaditoapk.remote.ApiAdapter
import com.testapp.mercaditoapk.remote.ApiImage
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response

class RepositoryImage {
    private val apiImage: ApiImage by lazy {
        ApiAdapter.getInstance().create(ApiImage::class.java)
    }

    suspend fun downloadStudentImage(studentId: Long): Result<ResponseBody?> {
        return try {
            val response: Response<ResponseBody> = apiImage.downloadStudentImage(studentId)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("Failed to download student image: ${response.message()}"))
            }
        } catch (e: Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }

    suspend fun downloadPublicationImage(imageId: Long): Result<ResponseBody?> {
        return try {
            val response: Response<ResponseBody> = apiImage.downloadPublicationImage(imageId)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("Failed to download publication image: ${response.message()}"))
            }
        } catch (e: Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }

    suspend fun uploadImage(files: List<MultipartBody.Part>, idPublication: Long, idStudent: Long): Result<String> {
        return try {
            val response: Response<String> = apiImage.uploadImage(files, idPublication, idStudent)
            if (response.isSuccessful) {
                Result.success(response.body() ?: "Image uploaded successfully")
            } else {
                Result.failure(Exception("Failed to upload image"))
            }
        } catch (e: Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }

    suspend fun deleteImage(id: Long): Result<String> {
        return try {
            val response: Response<String> = apiImage.deleteImage(id)
            if (response.isSuccessful) {
                Result.success(response.body() ?: "Image deleted successfully")
            } else {
                Result.failure(Exception("Failed to delete image"))
            }
        } catch (e: Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }

    suspend fun getImagesIDs(publicationId: Long): Result<List<Int>> {
        return try {
            val response: Response<List<Int>> = apiImage.getImagesIDs(publicationId)
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch image IDs"))
            }
        } catch (e: Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }
}
