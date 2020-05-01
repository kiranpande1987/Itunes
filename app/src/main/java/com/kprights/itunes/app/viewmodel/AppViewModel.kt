package com.kprights.itunes.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kprights.itunes.app.common.EntryDao
import com.kprights.itunes.app.model.DBEntry


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/04/20
 * Time : 10:57 PM
 */

class AppViewModel(database: EntryDao, application: Application) : AndroidViewModel(application) {

    private var repository = AppRepository(database)
    val dbEntries: LiveData<List<DBEntry>> = Transformations.map(repository.dbEntries) { it }
    val status: LiveData<AppRepository.ApiStatus> = Transformations.map(repository.status) { it }

    override fun onCleared() {
        super.onCleared()
        repository.cancel()
    }
}