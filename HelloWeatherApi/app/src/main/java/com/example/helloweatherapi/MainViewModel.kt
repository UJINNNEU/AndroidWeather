package com.example.helloweatherapi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val liveDataCurrent = MutableLiveData<String>() // для сегодняшего дня
    val liveDataList = MutableLiveData<List<String>>() // для других дней

}