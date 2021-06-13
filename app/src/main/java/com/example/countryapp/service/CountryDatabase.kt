package com.example.countryapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.countryapp.model.Country

@Database(entities = [Country::class], version = 1)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao


    // as Singleton
    companion object {

        // volatile for different threads
        @Volatile
        private var instance: CountryDatabase? = null
        private var lock = Any()

        // if no instance, just one thread is use
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDatabase(context).also {
                instance = it
            }


        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, CountryDatabase::class.java, "database_country"
        )
            .build()


    }
}