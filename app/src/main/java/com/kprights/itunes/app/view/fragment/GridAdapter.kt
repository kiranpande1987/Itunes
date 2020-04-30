package com.kprights.itunes.app.view.fragment

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kprights.itunes.app.model.Entry
import com.kprights.itunes.app.view.customview.GridItem


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/04/20
 * Time : 11:03 PM
 */

class GridAdapter : ListAdapter<Entry, GridItem>(EntryDiffCallback()) {
    // To create News Feed List Item View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GridItem.from(parent)

    // Bind Single News to NewsListItem
    override fun onBindViewHolder(holder: GridItem, position: Int) {
        val entry = getItem(position)
        holder.bind(entry)
    }

    // New Difference Callback : To check differences between old list and new list of News.
    class EntryDiffCallback : DiffUtil.ItemCallback<Entry>() {
        override fun areItemsTheSame(oldItem: Entry, newItem: Entry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Entry, newItem: Entry): Boolean {
            return oldItem == newItem
        }
    }
}