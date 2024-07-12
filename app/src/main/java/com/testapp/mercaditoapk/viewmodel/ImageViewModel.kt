package com.testapp.mercaditoapk.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.mercaditoapk.repository.RepositoryImage
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.ResponseBody

class ImageViewModel : ViewModel() {
    private val repositoryImage = RepositoryImage()

    private val _studentImage = MutableLiveData<Bitmap?>()
    val studentImage: LiveData<Bitmap?> get() = _studentImage

    private val _publicationImage = MutableLiveData<Bitmap?>()
    val publicationImage: LiveData<Bitmap?> get() = _publicationImage

    private val _uploadResult = MutableLiveData<String>()
    val uploadResult: LiveData<String> get() = _uploadResult

    private val _deleteResult = MutableLiveData<String>()
    val deleteResult: LiveData<String> get() = _deleteResult

    private val _imageIDs = MutableLiveData<List<Long>>()
    val imageIDs: LiveData<List<Long>> get() = _imageIDs

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun downloadStudentImage(studentId: Long) {
        viewModelScope.launch {
            val result = repositoryImage.downloadStudentImage(studentId)
            result.onSuccess {
                _studentImage.value = it
            }.onFailure {
                _errorMessage.value = it.message
            }
        }
    }

    fun getImagesForPublications(publicationIds: List<Long>) {
        viewModelScope.launch {
            _loading.value = true
            val allImageIds = mutableListOf<Long>()
            for (id in publicationIds) {
                val result = repositoryImage.getImagesIDs(id)
                result.fold(
                    onSuccess = {
                        allImageIds.addAll(it)
                        Log.d("ImageViewModel", "Image IDs for publication $id: $it")
                    },
                    onFailure = {
                        _errorMessage.value = it.message
                    }
                )
            }
            _imageIDs.value = allImageIds
            Log.d("ImageViewModel", "All image IDs: $allImageIds")
            _loading.value = false
        }
    }

    fun getImagesIDs(publicationId: Long) {
        viewModelScope.launch {
            val result = repositoryImage.getImagesIDs(publicationId)
            result.fold(
                onSuccess = {
                    _imageIDs.value = it
                    Log.d("ImageViewModel", "Image IDs for publication $publicationId: $it")
                },
                onFailure = {
                    _errorMessage.value = it.message
                }
            )
        }
    }

    fun downloadPublicationImage(imageId: Long) {
        viewModelScope.launch {
            val result = repositoryImage.downloadPublicationImage(imageId)
            result.onSuccess {
                _publicationImage.value = it
            }.onFailure {
                _errorMessage.value = it.message
            }
        }
    }

    fun uploadImage(files: List<MultipartBody.Part>, idPublication: Long, idStudent: Long) {
        viewModelScope.launch {
            val result = repositoryImage.uploadImage(files, idPublication, idStudent)
            result.fold(
                onSuccess = {
                    _uploadResult.value = it
                },
                onFailure = {
                    _errorMessage.value = it.message
                }
            )
        }
    }

    fun deleteImage(id: Long) {
        viewModelScope.launch {
            val result = repositoryImage.deleteImage(id)
            result.fold(
                onSuccess = {
                    _deleteResult.value = it
                },
                onFailure = {
                    _errorMessage.value = it.message
                }
            )
        }
    }
}