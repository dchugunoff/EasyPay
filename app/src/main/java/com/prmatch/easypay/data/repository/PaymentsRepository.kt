package com.prmatch.easypay.data.repository

import com.prmatch.easypay.data.model.payments_entity.PaymentsEntity
import com.prmatch.easypay.data.remote.PaymentsRetrofitClient

class PaymentsRepository(private val paymentsRetrofitClient: PaymentsRetrofitClient) {

    suspend fun getPaymentsData(token: String): PaymentsEntity {
        return paymentsRetrofitClient.instance.getPaymentsData(token)
    }
}