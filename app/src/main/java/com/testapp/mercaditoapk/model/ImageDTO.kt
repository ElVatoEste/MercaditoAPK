package com.testapp.mercaditoapk.model

data class ImageDTO(
    val id: Long? = null,
    val imageData: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImageDTO

        return imageData.contentEquals(other.imageData)
    }

    override fun hashCode(): Int {
        return imageData.contentHashCode()
    }

}