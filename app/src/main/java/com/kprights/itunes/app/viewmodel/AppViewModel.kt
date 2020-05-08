package com.kprights.itunes.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kprights.itunes.app.common.DatabaseService
import com.kprights.itunes.app.common.isOnline
import com.kprights.itunes.app.model.BaseModel
import com.kprights.itunes.app.model.Entry
import com.kprights.itunes.app.view.activity.MainActivity
import kotlinx.coroutines.Dispatchers


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/04/20
 * Time : 10:57 PM
 */

class AppViewModel(application: Application) : AndroidViewModel(application) {

    var repository =
        AppRepository(DatabaseService.getInstance(application).entryDao, Dispatchers.Main)
    val dbEntries: LiveData<List<Entry>> =
        Transformations.map(repository.dbEntries) { filterEntries(it, "m") }

    private fun filterEntries(baseModel: BaseModel?, filterBy: String): List<Entry>? {
        return baseModel?.feed?.entry?.filter { it.name.name.startsWith(filterBy, true) }
    }

    val status: LiveData<AppRepository.ApiStatus> =
        Transformations.map(repository.status) { offlineData(it) }

    private fun offlineData(it: AppRepository.ApiStatus): AppRepository.ApiStatus {
        if (!isOnline(MainActivity.context) && !dbEntries.value.isNullOrEmpty()) {
            return AppRepository.ApiStatus.OFFLINE
        }

        return it
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancel()
    }

    fun refreshView() {
        repository.refreshData()
    }
}