package com.kprights.itunes.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kprights.itunes.app.databinding.GridFragmentBinding
import com.kprights.itunes.app.viewmodel.AppViewModel
import com.kprights.itunes.app.viewmodel.AppViewModelFactory


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/04/20
 * Time : 10:31 PM
 */

class GridFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = GridFragmentBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Set Database Access Object for NewsFeed
        val application = requireNotNull(this.activity).application
        val appViewModelFactory = AppViewModelFactory(application)
        val viewModel = ViewModelProvider(this, appViewModelFactory).get(AppViewModel::class.java)

        // Giving the binding access to the ViewModel
        binding.viewModel = viewModel

        // RecyclerView To Adapter
        binding.recyclerView.adapter = GridAdapter(GridAdapter.OnClickListener {
            this.findNavController().navigate(GridFragmentDirections.actionShowDetail(it))
        })

        return binding.root
    }
}