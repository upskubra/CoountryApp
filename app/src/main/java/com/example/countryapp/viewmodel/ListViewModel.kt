package com.example.countryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp.model.Country

class ListViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryErrorMessage = MutableLiveData<Boolean>()
    val countryProgressBar = MutableLiveData<Boolean>()

    fun refreshData() {

        // example data
        val turkey = Country("Turkey", "Ankara", "Asia", "TRY", "Turkish", "www.test.com")
        val turkey1 = Country("Turkey1", "Ankara1", "Asia1", "TRY1", "Turkish1", "www.test.com")
        val turkey2 = Country("Turkey2", "Ankara2", "Asia2", "TRY2", "Turkish2", "www.test.com")

        val countryList = arrayListOf<Country>(turkey, turkey1, turkey2)
        countries.value = countryList
        countryErrorMessage.value = false
        countryProgressBar.value = false

    }

}