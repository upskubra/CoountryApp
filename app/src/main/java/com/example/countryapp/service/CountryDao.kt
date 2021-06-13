package com.example.countryapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.countryapp.model.Country

@Dao
interface CountryDao {

    @Insert // insert into
    suspend fun insertAll(vararg countries :  Country) : List<Long>

    // suspend coroutines, pause & resume
    //vararg multiple Country object, we do not how many
    // Lis<Long> for Primary Keys

    // for ListFragment
    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query ("SELECT * FROM country WHERE uuid = :countryId" )
    suspend fun getCountry(countryId : Int) : Country

    @Query ( "DELETE FROM  country")
    suspend fun deleteAllCountries()


}