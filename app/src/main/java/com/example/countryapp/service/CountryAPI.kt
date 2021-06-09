package com.example.countryapp.service

import com.example.countryapp.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    // GET, POST vb.

    //ext ->atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    //BASE_URL ->https://raw.githubusercontent.com/

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")

    fun getCountries(): Single<List<Country>>


}