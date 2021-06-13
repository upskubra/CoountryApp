package com.example.countryapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomSharedPreferences {

    companion object {

        private var sharedPreferences: SharedPreferences? = null
        var sharedPref = "time"

        @Volatile
        private var instance: CustomSharedPreferences? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {

            instance ?: createSharedPreferences(context).also {
                instance = it


            }
        }

        private fun createSharedPreferences(context: Context): CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

            return CustomSharedPreferences()


        }

    }

    fun saveTime(time: Long) {
        sharedPreferences?.edit(commit = true){
            putLong(sharedPref,time)
        }
    }

    fun getTime() = sharedPreferences?.getLong(sharedPref, 0)
}
