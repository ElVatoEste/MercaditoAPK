package com.testapp.mercaditoapk.model

data class PublicationDTO(
    val id: Long,
    val studentCIF: Long,
    val imageList: List<Image>,
    val title: String,
    val description: String,
    val price: Double,
    val isFeatured: Boolean,
    val availabilityType: AvailabilityType,
    val observations: String,
    val isVisible: Boolean,
)