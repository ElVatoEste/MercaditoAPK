package com.testapp.mercaditoapk.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Publication(
    @PrimaryKey
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


