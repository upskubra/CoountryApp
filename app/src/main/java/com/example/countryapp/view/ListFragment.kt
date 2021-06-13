package com.example.countryapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countryapp.R.layout.fragment_list
import com.example.countryapp.adapter.CountryAdapter
import com.example.countryapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fragment - viewModel
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refreshData()

        //  alt alta diz
        recyclerView.layoutManager = LinearLayoutManager(context)

        // adapter-recyclerView connected
        recyclerView.adapter = countryAdapter

        observeLiveData()

        swipeRefreshLayout.setOnRefreshListener{
            recyclerView.visibility = View.GONE
            progressBar.visibility =View.GONE
            progressBar.visibility = View.VISIBLE
            viewModel.refreshDataFromAPI()
            swipeRefreshLayout.isRefreshing = false

        }



    }


    private fun observeLiveData() {

        viewModel.countries.observe(viewLifecycleOwner, Observer { it ->

            it?.let {
                recyclerView.visibility = View.VISIBLE
                countryAdapter.updateCountryList(it)
            }
        })

        viewModel.countryErrorMessage.observe(viewLifecycleOwner, Observer { error ->

            error?.let {
                if (it) {
                    // have an error
                    errorMessageTextView.visibility = View.VISIBLE


                } else {
                    errorMessageTextView.visibility = View.GONE
                }
            }

        })
        viewModel.countryProgressBar.observe(viewLifecycleOwner, Observer { error ->

            error?.let {
                if (it) {
                    // loading
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    errorMessageTextView.visibility = View.GONE



                } else {
                    progressBar.visibility = View.GONE

                }
            }

        })


    }


}