package com.testapp.mercaditoapk.model


data class Publication(
    val id: Long,
    val studentCIF: Long,
    val imageList: List<Image>,
    val title: String,
    val description: String,
    val categoryId: Long,
    val price: Double,
    val isFeatured: Boolean,
    val availabilityType: AvailabilityType,
    val observations: String,
    val isVisible: Boolean
    /*val messagingList: Messaging,
    val purchaseList: Purchase
    */
)
/*val cif: Long,
val name: String,
val surname: String,
val email: String,
val password: String,
val phoneNumber: String,
val personalDescription: String,
val following: Set<Publication>?,
val followers: Set<Publication>?*/

/*
    val publicationList: List<Publication>?,
    val sentMessages: List<Messaging>?,
    val receivedMessages: List<Messaging>?,
    val ticketList: List<Ticket>?,
    val mainCommentList: List<MainComment>?,
    val purchaseList: List<Purchase>?
*/


