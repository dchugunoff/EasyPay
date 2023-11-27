package com.prmatch.easypay.data.remote

import com.prmatch.easypay.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PaymentsRetrofitClient {

    val instance: PaymentsApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PaymentsApiService::class.java)
    }
}