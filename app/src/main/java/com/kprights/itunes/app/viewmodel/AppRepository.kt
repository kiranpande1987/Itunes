package com.kprights.itunes.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kprights.itunes.app.common.EntryDao
import com.kprights.itunes.app.model.BaseModel
import com.kprights.itunes.app.model.DBEntry
import kotlinx.coroutines.*


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/04/20
 * Time : 11:00 PM
 */

class AppRepository(database: EntryDao) {

    enum class ApiStatus { LOADING, ERROR, DONE, OFFLINE }

    private val remoteDataSource = RemoteDataSource()
    private val localDataSource = LocalDataSource(database)

    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    val status: MutableLiveData<ApiStatus> = MutableLiveData<ApiStatus>()
    val dbEntries: LiveData<List<DBEntry>> = localDataSource.getAllEntries()

    init {
        updateFromRemote()
    }

    private fun updateFromRemote() {
        scope.launch {
            try {
                status.value = ApiStatus.LOADING

                val model = remoteDataSource.getFeed()

                withContext(Dispatchers.IO)
                {
                    localDataSource.deleteAllEntries()
                    insertIntoDB(model)
                }

                status.value = ApiStatus.DONE
            } catch (e: Exception) {
                status.value = ApiStatus.ERROR
            }
        }
    }

    private fun insertIntoDB(model: BaseModel) {

        model.feed.entry.forEach {
            localDataSource.saveEntries(
                DBEntry().copy(
                    name = it.name.name,
                    image = it.image[it.image.size - 1].label,
                    currency = it.price.attributes.currency,
                    amount = it.price.attributes.amount,
                    artist = it.artist.label
                )
            )
        }
    }

    fun cancel() {
        job.cancel()
    }

    fun refreshData() {
        updateFromRemote()
    }
}