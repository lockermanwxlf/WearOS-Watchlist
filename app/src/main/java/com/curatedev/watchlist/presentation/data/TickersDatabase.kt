package com.curatedev.watchlist.presentation.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Ticker::class],
    version = 1,
    exportSchema = false
)
abstract class TickersDatabase: RoomDatabase() {
    abstract fun tickersDao(): TickersDao
}