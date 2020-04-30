package com.kprights.itunes.app.viewmodel

import androidx.lifecycle.MutableLiveData
import com.kprights.itunes.app.model.BaseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/04/20
 * Time : 11:00 PM
 */

class AppRepository {
    private val remoteDataSource = RemoteDataSource()
    private val localDataSource = LocalDataSource()

    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    private var forceUpdate: Boolean = true
    val baseModel: MutableLiveData<BaseModel> = MutableLiveData<BaseModel>()

    init {
        scope.launch {
            Timber.e("Call STart")
            val model = remoteDataSource.getFeed()
            baseModel.value = model
            Timber.e("Call Complete")
            Timber.e("Data : ${baseModel.value}")
        }
    }

    fun cancel() {
        job.cancel()
    }
}