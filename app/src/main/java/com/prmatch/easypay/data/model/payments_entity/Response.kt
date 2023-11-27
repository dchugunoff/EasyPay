package com.prmatch.easypay.data.model.payments_entity

data class Response(
    val amount: String,
    val created: Double,
    val id: Int,
    val title: String
)