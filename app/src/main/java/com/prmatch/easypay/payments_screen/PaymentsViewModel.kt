package com.prmatch.easypay.payments_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmatch.easypay.data.model.payments_entity.Response
import com.prmatch.easypay.data.remote.PaymentsRetrofitClient
import com.prmatch.easypay.data.repository.PaymentsRepository
import com.prmatch.easypay.utils.DataState
import kotlinx.coroutines.launch

class PaymentsViewModel : ViewModel() {

    private val repository = PaymentsRepository(PaymentsRetrofitClient)

    private val _paymentsList = MutableLiveData<List<Response>>()
    val paymentsList = _paymentsList

    private val _paymentsDataState = MutableLiveData<DataState>()
    val paymentDataState: LiveData<DataState> = _paymentsDataState

    fun getPayments(token: String) {
        _paymentsDataState.value = DataState.Loading
        viewModelScope.launch {
            try {
                _paymentsList.value = repository.getPaymentsData(token).response
                _paymentsDataState.value = DataState.Success
            } catch (e: Exception) {
                _paymentsDataState.value = DataState.Error
            }
        }
    }
}