package com.prmatch.easypay.data.repository

import com.prmatch.easypay.data.model.login_entity.LoginEntity
import com.prmatch.easypay.data.model.login_entity.LoginRequest
import com.prmatch.easypay.data.remote.LoginRetrofitClient

class LoginRepository(private val loginClient: LoginRetrofitClient) {

    suspend fun login(loginRequest: LoginRequest): LoginEntity {
        return loginClient.instance.login(loginRequest)
    }
}