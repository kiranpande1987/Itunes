package com.kprights.itunes.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kprights.itunes.app.model.Entry


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/04/20
 * Time : 10:57 PM
 */

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = AppRepository()
    val entry: LiveData<List<Entry>> =
        Transformations.map(repository.baseModel) { model -> model.feed.entry }
    val status: LiveData<AppRepository.ApiStatus> = Transformations.map(repository.status) { it }

    override fun onCleared() {
        super.onCleared()
        repository.cancel()
    }
}