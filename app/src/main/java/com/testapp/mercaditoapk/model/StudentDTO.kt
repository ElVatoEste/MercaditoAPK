package com.testapp.mercaditoapk.model

data class StudentDTO(
    val CIF: Long,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val personalDescription: String
) {
}