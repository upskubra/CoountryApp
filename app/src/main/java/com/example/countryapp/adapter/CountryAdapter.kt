package com.example.countryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.R
import com.example.countryapp.model.Country
import kotlinx.android.synthetic.main.country_recycler_row.view.*
import java.util.*
import kotlin.collections.ArrayList

class CountryAdapter(
    val countryArrayList: ArrayList<Country>)
    : RecyclerView.Adapter<CountryAdapter.CountryVH>() {

    class CountryVH(var view : View) : RecyclerView.ViewHolder(view) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryAdapter.CountryVH {

        val inflater = LayoutInflater.from(parent.context)
        // model-recyclerView connected
        val view = inflater.inflate(R.layout.country_recycler_row, parent, false)
        return CountryVH(view)
    }

    override fun onBindViewHolder(holder: CountryAdapter.CountryVH, position: Int) {
        holder.view.countryNameTextView.text = countryArrayList[position].countryName
        holder.view.countryRegionNameTextView.text = countryArrayList[position].countryRegion
        // image coming soon with glide
    }

    override fun getItemCount(): Int {
         return countryArrayList.size
    }
    fun updateCountryList(newCountryArrayList : ArrayList<Country> ){
        countryArrayList.clear()
        countryArrayList.addAll(newCountryArrayList)
        notifyDataSetChanged()

    }
}

