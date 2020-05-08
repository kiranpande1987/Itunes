package com.kprights.itunes.app.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.kprights.itunes.app.R
import com.kprights.itunes.app.model.Entry
import com.kprights.itunes.app.model.Image
import com.kprights.itunes.app.view.fragment.GridAdapter
import com.kprights.itunes.app.viewmodel.AppRepository.ApiStatus
import com.kprights.itunes.app.viewmodel.AppViewModel


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 11/04/20
 * Time : 12:28 AM
 */

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, images: List<Image>) {

    val image = images[images.size - 1].label

    image.let {

        val imgUri = image.toUri().buildUpon().scheme("https").build()
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

        ApiStatus.OFFLINE -> {
            statusImageView.visibility = View.GONE
            val snack = Snackbar.make(
                statusImageView,
                "You Are Looking Offline Data.",
                Snackbar.LENGTH_LONG
            )
            snack.show()
        }
    }
}

@BindingAdapter(value = ["currency", "amount"])
fun format(textView: TextView, currency: String, amount: String) {

    val a = amount.toDoubleOrNull()

    textView.text = if (a != null && a > 0) {
        "$currency $a"
    } else {
        "Free"
    }
}

@BindingAdapter("onRefreshListener")
fun swipeToRefresh(swipeRefreshLayout: SwipeRefreshLayout, appViewModel: AppViewModel) {
    swipeRefreshLayout.setOnRefreshListener {
        swipeRefreshLayout.isRefreshing = true
        appViewModel.refreshView()
        swipeRefreshLayout.isRefreshing = false
    }
}

@BindingAdapter("filterList")
fun filterList(spinner: Spinner, list: List<String>) {
    val adapter = spinner.adapter as ArrayAdapter<String>
    adapter.addAll(list)
    adapter.notifyDataSetChanged()
}

fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
        }
    }
    return false
}



