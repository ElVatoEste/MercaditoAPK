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
    val followers: Set<Student>?,

    /*
        val publicationList: List<Publication>?,
        val sentMessages: List<Messaging>?,
        val receivedMessages: List<Messaging>?,
        val ticketList: List<Ticket>?,
        val mainCommentList: List<MainComment>?,
        val purchaseList: List<Purchase>?
    */
    )

