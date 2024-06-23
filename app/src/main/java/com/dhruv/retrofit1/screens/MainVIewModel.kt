package com.dhruv.retrofit1.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhruv.retrofit1.model.Pincode
import com.dhruv.retrofit1.networking.RetrofitInstance
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _pincodeData = MutableLiveData<List<Pincode>>()
    val pincodeData: LiveData<List<Pincode>>
        get() = _pincodeData
    init {
        getPincodeData()
    }
    fun getPincodeData() {
        viewModelScope.launch {
            _pincodeData.value = RetrofitInstance.apiService.getPincodeData()
        }
    }
}