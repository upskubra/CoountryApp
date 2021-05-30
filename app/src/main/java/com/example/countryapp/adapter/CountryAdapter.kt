package com.example.countryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.R
import com.example.countryapp.model.Country
import com.example.countryapp.view.ListFragmentDirections
import kotlinx.android.synthetic.main.country_recycler_row.view.*

class CountryAdapter(
    val countryArrayList: ArrayList<Country>
) : RecyclerView.Adapter<CountryAdapter.CountryVH>() {

    class CountryVH(var view: View) : RecyclerView.ViewHolder(view) {

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

        // when click the item
        holder.view.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return countryArrayList.size
    }

    fun updateCountryList(newCountryArrayList: List<Country>) {
        countryArrayList.clear()
        countryArrayList.addAll(newCountryArrayList)
        notifyDataSetChanged()

    }
}

