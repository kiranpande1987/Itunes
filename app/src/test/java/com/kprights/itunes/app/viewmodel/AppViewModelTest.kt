package com.kprights.itunes.app.viewmodel

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Copyright (c) 2020 for KPrights
 *
 *
 * User : Kiran Pande
 * Date : 02/05/20
 * Time : 3:14 AM
 */

@RunWith(AndroidJUnit4::class)
class AppViewModelTest {
    @Test
    fun testViewModel() {
        val viewModel = AppViewModel(ApplicationProvider.getApplicationContext())

        Assert.assertEquals(AppRepository.ApiStatus.LOADING, viewModel.repository.status.value)

//        Assert.assertEquals(AppRepository.ApiStatus.DONE, viewModel.status.value)
//        Assert.assertEquals(AppRepository.ApiStatus.OFFLINE, viewModel.status.value)
//        Assert.assertEquals(AppRepository.ApiStatus.ERROR, viewModel.status.value)
    }
}