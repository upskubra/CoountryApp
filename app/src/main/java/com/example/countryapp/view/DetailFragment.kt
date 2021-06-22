package com.example.countryapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.countryapp.R
import com.example.countryapp.databinding.FragmentDetailBinding
import com.example.countryapp.viewmodel.DetailViewModel

class DetailFragment : Fragment() {
    private var countryUuid = 0
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        // Inflate the layout for this fragment
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = DetailFragmentArgs.fromBundle(it).countryUuid
        }

        // ViewModel- Fragment connected
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()

    }

    private fun observeLiveData() {

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->

            country?.let {

                dataBinding.country = it
                /* countryNaDetailTextView.text = country.countryName
                 countryCapitalDetailTextView.text = country.countryCapital
                 countryRegionDetailTextView.text = country.countryRegion
                 countryCurrencyDetailTextView.text = country.countryCurrency
                 countryLanguageTextView.text = country.countryLanguage
                 context?.let {
                     detailImageView.downloadFromUrl(country.countryImageUrl, placeholderProgressBar(it))

                 }
             */
            }

        })
    }


}