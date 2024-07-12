package com.testapp.mercaditoapk.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Image(
    @PrimaryKey
    val id: Long?,
    val name: String?,
    val type: String?,
    val idPublication: Long?,
    val student: Student?,
    val imageData: ByteArray
) {
    constructor() : this(null, null, null, null, null, ByteArray(0))

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Image

        return imageData.contentEquals(other.imageData)
    }

    override fun hashCode(): Int {
        return imageData.contentHashCode()
    }
}
