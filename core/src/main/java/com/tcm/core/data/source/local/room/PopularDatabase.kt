package com.tcm.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tcm.core.data.source.local.entity.PopularEntity

@Database(entities = [PopularEntity::class], version = 1, exportSchema = false)
abstract class PopularDatabase : RoomDatabase() {

    abstract fun popularDao(): PopularDao
}