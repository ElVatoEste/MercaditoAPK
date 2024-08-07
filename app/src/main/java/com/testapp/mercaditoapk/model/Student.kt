package com.testapp.mercaditoapk.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey
    val cif: Long,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val personalDescription: String,
    val following: Set<Student>?,
    val followers: Set<Student>?
)

/*
    val publicationList: List<Publication>?,
    val sentMessages: List<Messaging>?,
    val receivedMessages: List<Messaging>?,
    val ticketList: List<Ticket>?,
    val mainCommentList: List<MainComment>?,
    val purchaseList: List<Purchase>?
*/


