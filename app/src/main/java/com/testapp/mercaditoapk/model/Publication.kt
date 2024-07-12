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


