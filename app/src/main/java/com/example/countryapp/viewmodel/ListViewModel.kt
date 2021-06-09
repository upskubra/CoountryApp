package com.example.countryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp.model.Country
import com.example.countryapp.service.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    private val countryApiService = CountryAPIService()
    // use and at object, required for memory management
    private val disposable = CompositeDisposable()


    val countries = MutableLiveData<List<Country>>()
    val countryErrorMessage = MutableLiveData<Boolean>()
    val countryProgressBar = MutableLiveData<Boolean>()

    fun refreshData() {

        getDataFromAPI()

    }

    fun getDataFromAPI() {

        countryProgressBar.value = true

        disposable.add(
            countryApiService.getData()
                    // different thread because asynchronous, working background
                .subscribeOn(Schedulers.newThread())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {

                        countries.value = t
                        countryErrorMessage.value = false
                        countryProgressBar.value = false

                    }

                    override fun onError(e: Throwable) {
                        countryErrorMessage.value = true
                        countryProgressBar.value = false
                        e.printStackTrace()

                    }

                })
        )



    }

}