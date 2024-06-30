package com.testapp.mercaditoapk.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.mercaditoapk.model.Student
import com.testapp.mercaditoapk.model.StudentDTO
import com.testapp.testapp.repository.RepositoryStudent
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {
    private val repository = RepositoryStudent()

    private val _allStudents = MutableLiveData<Result<List<Student>>>()
    val allStudents: LiveData<Result<List<Student>>> get() = _allStudents

    private val _studentById = MutableLiveData<Result<Student?>>()
    val studentById: LiveData<Result<Student?>> get() = _studentById

    private val _createStudent = MutableLiveData<Result<String>>()
    val createStudent: LiveData<Result<String>> get() = _createStudent

    private val _deleteStudent = MutableLiveData<Result<String>>()
    val deleteStudent: LiveData<Result<String>> get() = _deleteStudent

    private val _updateStudent = MutableLiveData<Result<String>>()
    val updateStudent: LiveData<Result<String>> get() = _updateStudent

    private val _assignFollowing = MutableLiveData<Result<String>>()
    val assignFollowing: LiveData<Result<String>> get() = _assignFollowing

    private val _removeFollowing = MutableLiveData<Result<String>>()
    val removeFollowing: LiveData<Result<String>> get() = _removeFollowing

    fun fetchAllStudents() {
        viewModelScope.launch {
            _allStudents.value = repository.getAllStudents()
        }
    }

    fun fetchStudentById(cif: Long) {
        viewModelScope.launch {
            _studentById.value = repository.getStudentById(cif)
        }
    }

    fun createStudent(studentDTO: StudentDTO) {
        viewModelScope.launch {
            _createStudent.value = repository.createStudent(studentDTO)
        }
    }

    fun deleteStudent(cif: Long) {
        viewModelScope.launch {
            _deleteStudent.value = repository.deleteStudent(cif)
        }
    }

    fun updateStudent(studentDTO: StudentDTO) {
        viewModelScope.launch {
            _updateStudent.value = repository.updateStudent(studentDTO)
        }
    }

    fun assignFollowingToStudent(idFollowing: Long, idFollower: Long) {
        viewModelScope.launch {
            _assignFollowing.value = repository.assignFollowingToStudent(idFollowing, idFollower)
        }
    }

    fun removeFollowingFromStudent(idFollowing: Long, idFollower: Long) {
        viewModelScope.launch {
            _removeFollowing.value = repository.removeFollowingFromStudent(idFollowing, idFollower)
        }
    }
}