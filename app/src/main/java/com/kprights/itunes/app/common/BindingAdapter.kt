package com.kprights.itunes.app.common

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kprights.itunes.app.R
import com.kprights.itunes.app.model.Entry
import com.kprights.itunes.app.view.fragment.GridAdapter
import com.kprights.itunes.app.viewmodel.AppViewModel


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 11/04/20
 * Time : 12:28 AM
 */

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Entry>?) {
    val adapter = recyclerView.adapter as GridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: AppViewModel.ApiStatus?) {
    when (status) {
        AppViewModel.ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        AppViewModel.ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        AppViewModel.ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

