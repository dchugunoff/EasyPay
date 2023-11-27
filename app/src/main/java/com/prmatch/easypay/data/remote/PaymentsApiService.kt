package com.prmatch.easypay.data.remote

import com.prmatch.easypay.data.model.payments_entity.PaymentsEntity
import com.prmatch.easypay.utils.APP_KEY
import com.prmatch.easypay.utils.TOKEN
import com.prmatch.easypay.utils.VERSION
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface PaymentsApiService {

    @Headers(APP_KEY, VERSION)
    @GET("payments")
    suspend fun getPaymentsData(@Header(TOKEN) token: String): PaymentsEntity
}