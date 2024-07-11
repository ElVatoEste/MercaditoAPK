package com.testapp.mercaditoapk.model

data class Image(

    val id: Long? = null,
    val name: String? = null,
    val type: String? = null,
    val idPublication: Long? = null,
    val student: Student? = null,
    val imageData: ByteArray
)
