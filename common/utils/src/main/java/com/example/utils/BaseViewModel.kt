package com.example.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel(){
    protected fun <T> LiveData<T>.setValue(value:T){
        (this as? MutableLiveData)?.value = value
    }

    protected fun <T> MutableLiveData<T>.asNonMutable() : LiveData<T> {
        return this
    }
}