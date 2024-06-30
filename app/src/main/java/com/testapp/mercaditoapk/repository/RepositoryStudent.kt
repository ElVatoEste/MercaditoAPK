package com.testapp.mercaditoapk.repository

import android.util.Log
import com.testapp.mercaditoapk.model.Student
import com.testapp.mercaditoapk.model.StudentDTO
import com.testapp.mercaditoapk.remote.ApiAdapter
import com.testapp.mercaditoapk.remote.ApiStudent
import retrofit2.Response

class RepositoryStudent {
    private val apiStudent: ApiStudent by lazy {
        ApiAdapter.getInstance().create(ApiStudent::class.java)
    }

    suspend fun getAllStudents(): Result<List<Student>> {
        return try {
            val response: Response<List<Student>> = apiStudent.getAllStudents()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch students"))
            }
        } catch (e: Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }

    suspend fun getStudentById(cif: Long): Result<Student?> {
        return try {
            val response: Response<Student> = apiStudent.getStudentById(cif)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("Failed to fetch student by ID"))
            }
        } catch (e: Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }

    suspend fun createStudent(studentDTO: StudentDTO): Result<String> {
        return try {
            val response: Response<String> = apiStudent.createStudent(studentDTO)
            if (response.isSuccessful) {
                Result.success(response.body() ?: "Student created successfully")
            } else {
                Result.failure(Exception("Failed to create student"))
            }
        } catch (e: Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }

    suspend fun deleteStudent(cif: Long): Result<String> {
        return try {
            val response: Response<String> = apiStudent.deleteStudent(cif)
            if (response.isSuccessful) {
                Result.success(response.body() ?: "Student deleted successfully")
            } else {
                Result.failure(Exception("Failed to delete student"))
            }
        } catch (e: Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }

    suspend fun updateStudent(studentDTO: StudentDTO): Result<String> {
        return try {
            val response: Response<String> = apiStudent.updateStudent(studentDTO)
            if (response.isSuccessful) {
                Result.success(response.body() ?: "Student updated successfully")
            } else {
                Result.failure(Exception("Failed to update student"))
            }
        } catch (e: Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }

    suspend fun assignFollowingToStudent(idFollowing: Long, idFollower: Long): Result<String> {
        return try {
            val response: Response<String> = apiStudent.assignFollowingToStudent(idFollowing, idFollower)
            if (response.isSuccessful) {
                Result.success(response.body() ?: "Following assigned successfully")
            } else {
                Result.failure(Exception("Failed to assign following"))
            }
        } catch (e: Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }

    suspend fun removeFollowingFromStudent(idFollowing: Long, idFollower: Long): Result<String> {
        return try {
            val response: Response<String> = apiStudent.removeFollowingFromStudent(idFollowing, idFollower)
            if (response.isSuccessful) {
                Result.success(response.body() ?: "Following removed successfully")
            } else {
                Result.failure(Exception("Failed to remove following"))
            }
        } catch (e: Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }
}
