package com.testapp.mercaditoapk.model


data class Student(
    val CIF: Long,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val profilePhoto: ByteArray?,
    val phoneNumber: String,
    val personalDescription: String,
    val following: Set<Student>?,
    val followers: Set<Student>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        if (CIF != other.CIF) return false
        if (name != other.name) return false
        if (surname != other.surname) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (profilePhoto != null) {
            if (other.profilePhoto == null) return false
            if (!profilePhoto.contentEquals(other.profilePhoto)) return false
        } else if (other.profilePhoto != null) return false
        if (phoneNumber != other.phoneNumber) return false
        if (personalDescription != other.personalDescription) return false
        if (following != other.following) return false
        if (followers != other.followers) return false

        return true
    }

    override fun hashCode(): Int {
        var result = CIF.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + surname.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + (profilePhoto?.contentHashCode() ?: 0)
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + personalDescription.hashCode()
        result = 31 * result + (following?.hashCode() ?: 0)
        result = 31 * result + (followers?.hashCode() ?: 0)
        return result
    }
}

/*
    val publicationList: List<Publication>?,
    val sentMessages: List<Messaging>?,
    val receivedMessages: List<Messaging>?,
    val ticketList: List<Ticket>?,
    val mainCommentList: List<MainComment>?,
    val purchaseList: List<Purchase>?
*/


