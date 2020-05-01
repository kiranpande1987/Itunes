package com.kprights.itunes.app.view.customview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kprights.itunes.app.databinding.GridItemBinding
import com.kprights.itunes.app.model.DBEntry
import com.kprights.itunes.app.view.fragment.GridAdapter


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/04/20
 * Time : 10:31 PM
 */

class GridItem(private val binding: GridItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(entry: DBEntry, onClickListener: GridAdapter.OnClickListener) {
        binding.entry = entry
        binding.onClickListener = onClickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): GridItem {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = GridItemBinding.inflate(layoutInflater, parent, false)
            return GridItem(binding)
        }
    }
}