package com.kprights.itunes.app.common

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kprights.itunes.app.R
import com.kprights.itunes.app.model.DBEntry
import com.kprights.itunes.app.view.fragment.GridAdapter
import com.kprights.itunes.app.viewmodel.AppRepository.ApiStatus


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 11/04/20
 * Time : 12:28 AM
 */

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, images: String) {
    images.let {

        val imgUri = images.toUri().buildUpon().scheme("https").build()
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
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DBEntry>?) {
    val adapter = recyclerView.adapter as GridAdapter
    adapter.submitList(data)
}


@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter(value = ["currency", "amount"])
fun format(textView: TextView, currency: String, amount: String) {

    var a = amount.toDoubleOrNull()

    textView.text = if (a != null && a > 0) {
        "$currency $a"
    } else {
        "Free"
    }
}

