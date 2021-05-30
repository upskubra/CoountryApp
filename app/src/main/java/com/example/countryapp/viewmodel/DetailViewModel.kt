package com.example.countryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp.model.Country

class DetailViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom() {
        val country = Country(
            "Turkey", "Ankara", "Asia",
            "TRY", "Turkish", "www.test.com"
        )

        countryLiveData.value = country
    }
}