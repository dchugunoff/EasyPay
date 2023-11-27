package com.prmatch.easypay.login_screen

import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.prmatch.easypay.Application
import com.prmatch.easypay.R
import com.prmatch.easypay.data.model.login_entity.LoginRequest
import com.prmatch.easypay.data.remote.LoginRetrofitClient
import com.prmatch.easypay.data.repository.LoginRepository
import com.prmatch.easypay.utils.DataState
import com.prmatch.easypay.utils.SharedPreferencesHelper
import kotlinx.coroutines.launch

class LoginScreenViewModel(private val application: Application) : AndroidViewModel(Application()) {

    /**
     * AndroidViewModel для получения context для sharedPreferencesHelper, т.к мы не используем DI
     */

    private val _loginState = MutableLiveData<DataState>()
    val loginState: LiveData<DataState> = _loginState

    private val repository = LoginRepository(LoginRetrofitClient)

    private val sharedPreferences =
        SharedPreferencesHelper(application.applicationContext)

    fun login(login: String, password: String) {
        viewModelScope.launch {
            _loginState.value = DataState.Loading
            try {
                val loginData = repository.login(LoginRequest(login, password))
                if (loginData.success == "true") {
                    val token = loginData.response.token
                    sharedPreferences.setToken(token)
                    _loginState.value = DataState.Success
                } else {
                    _loginState.value = DataState.Error
                    Toast.makeText(
                        application.applicationContext,
                        application.applicationContext.getString(R.string.check_log_and_password),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                _loginState.value = DataState.Error
            }
        }
    }


}