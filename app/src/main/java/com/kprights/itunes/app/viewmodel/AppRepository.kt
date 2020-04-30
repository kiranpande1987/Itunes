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

    enum class ApiStatus { LOADING, ERROR, DONE }

    private val remoteDataSource = RemoteDataSource()
    private val localDataSource = LocalDataSource()

    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    val baseModel: MutableLiveData<BaseModel> = MutableLiveData<BaseModel>()
    val status: MutableLiveData<ApiStatus> = MutableLiveData<ApiStatus>()

    init {
        scope.launch {

            try {
                Timber.e("Call STart")
                status.value = ApiStatus.LOADING
                val model = remoteDataSource.getFeed()
                baseModel.value = model
                status.value = ApiStatus.DONE
                Timber.e("Call Complete")
                Timber.e("Data : ${baseModel.value}")
            } catch (e: Exception) {
                status.value = ApiStatus.ERROR
            }
        }
    }

    fun cancel() {
        job.cancel()
    }
}