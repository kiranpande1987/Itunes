package com.kprights.itunes.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kprights.itunes.app.model.BaseModel
import com.kprights.itunes.app.model.Entry


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/04/20
 * Time : 10:57 PM
 */

class AppViewModel(application: Application) : AndroidViewModel(application) {
    enum class ApiStatus { LOADING, ERROR, DONE }

    private var repository = AppRepository()
    val newsFeed: LiveData<BaseModel> = Transformations.map(repository.baseModel) { it }
    val entry: LiveData<List<Entry>> = Transformations.map(newsFeed) { model -> model.feed.entry }

    override fun onCleared() {
        super.onCleared()
        repository.cancel()
    }
}