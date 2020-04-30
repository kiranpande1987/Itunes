package com.kprights.itunes.app.common

import androidx.room.Dao


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 11/04/20
 * Time : 2:09 AM
 */

abstract class DatabaseService// : RoomDatabase()
{
//    abstract val newsFeedDao: NewsFeedDao
//
//    companion object{
//        @Volatile
//        private var INSTANCE: DatabaseService? = null
//
//        fun getInstance(context: Context) : DatabaseService
//        {
//            var instance = INSTANCE
//
//            if(instance == null)
//            {
//                instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    DatabaseService::class.java,
//                    "apple_database"
//                ).fallbackToDestructiveMigration()
//                    .build()
//
//                INSTANCE = instance
//            }
//
//            return instance
//        }
//    }
}

@Dao
interface EntryDao
