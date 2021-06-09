package com.example.countryapp.service

import com.example.countryapp.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {

    //ext ->atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    //BASE_URL ->https://raw.githubusercontent.com/

    private val BASE_URl = "https://raw.githubusercontent.com/"


    // API interface - Api Service Connected
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountryAPI::class.java)

    fun getData() : Single<List<Country>> {

        return api.getCountries()
    }
}