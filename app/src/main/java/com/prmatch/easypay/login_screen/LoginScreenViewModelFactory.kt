package com.prmatch.easypay.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prmatch.easypay.Application

class LoginScreenViewModelFactory(private val application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginScreenViewModel::class.java)) {
            return LoginScreenViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}