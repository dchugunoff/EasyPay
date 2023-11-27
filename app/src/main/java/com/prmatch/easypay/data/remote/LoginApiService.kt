package com.prmatch.easypay.data.remote

import com.prmatch.easypay.data.model.login_entity.LoginEntity
import com.prmatch.easypay.data.model.login_entity.LoginRequest
import com.prmatch.easypay.utils.APP_KEY
import com.prmatch.easypay.utils.VERSION
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApiService {

    @Headers(APP_KEY, VERSION)
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginEntity
}