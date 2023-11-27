package com.prmatch.easypay.utils

sealed class DataState {
    object Loading : DataState()
    object Success : DataState()
    object Error : DataState()
}
