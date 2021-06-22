package com.example.countryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.R
import com.example.countryapp.databinding.CountryRecyclerRowBinding
import com.example.countryapp.model.Country
import com.example.countryapp.util.downloadFromUrl
import com.example.countryapp.util.placeholderProgressBar
import com.example.countryapp.view.ListFragmentDirections
import kotlinx.android.synthetic.main.country_recycler_row.view.*

class CountryAdapter( val countryArrayList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryVH>(), CountryClickListener {

    class CountryVH(var view: CountryRecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryAdapter.CountryVH {

        val inflater = LayoutInflater.from(parent.context)
        // model-recyclerView connected

        /*val view = inflater.inflate(R.layout.country_recycler_row, parent, false)*/
        val view = DataBindingUtil.inflate<CountryRecyclerRowBinding>(inflater, R.layout.country_recycler_row, parent, false)

        return CountryVH(view)
    }

    override fun onBindViewHolder(holder: CountryAdapter.CountryVH, position: Int) {

        // After data binding

        /*holder.view.countryNameTextView.text = countryArrayList[position].countryName
        holder.view.countryRegionNameTextView.text = countryArrayList[position].countryRegion
        // image coming soon with glide

        holder.view.imageView.downloadFromUrl(countryArrayList[position].countryImageUrl, placeholderProgressBar(holder.view.context))

        // when click the item
        holder.view.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(countryArrayList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }*/
         // BEFORE DATA BINDING

        holder.view.country = countryArrayList[position]
        holder.view.listener = this



    }

    override fun getItemCount(): Int {
        return countryArrayList.size
    }

    fun updateCountryList(newCountryArrayList: List<Country>) {
        countryArrayList.clear()
        countryArrayList.addAll(newCountryArrayList)
        notifyDataSetChanged()

    }

    override fun onCountryClicked(v: View) {
        val uuid = v.uuidText.text.toString().toInt()
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}

