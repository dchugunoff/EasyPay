package com.prmatch.easypay.data.model.payments_entity

data class PaymentsEntity(
    val response: List<Response>,
    val success: String
)