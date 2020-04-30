package com.kprights.itunes.app.view.customview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kprights.itunes.app.R
import com.kprights.itunes.app.databinding.GridItemBinding
import com.kprights.itunes.app.model.Entry


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/04/20
 * Time : 10:31 PM
 */

class GridItem(private val binding: GridItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(entry: Entry) {
        binding.entry = entry
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): GridItem {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = GridItemBinding.inflate(layoutInflater, parent, false)
            return GridItem(binding)
        }

        @BindingAdapter("showImage")
        @JvmStatic
        fun showImage(imageViewForNewsFeed: ImageView, imageUrl: String?) {
            imageUrl?.let {
                val imgUri = imageUrl.toUri().buildUpon().scheme("http").build()

                Glide.with(imageViewForNewsFeed.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imageViewForNewsFeed)
            }
        }
    }
}