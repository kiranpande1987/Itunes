package com.kprights.itunes.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kprights.itunes.app.databinding.FragmentDetailBinding


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 30/04/20
 * Time : 3:51 PM
 */

class FragmentDetail : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.entry = FragmentDetailArgs.fromBundle(requireArguments()).entry

        return binding.root
    }
}