package com.curatedev.watchlist.presentation.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TickersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(vararg tickers: Ticker)

    @Delete
    abstract suspend fun delete(ticker: Ticker)

    @Query("SELECT * FROM ticker")
    abstract fun getAll(): Flow<List<Ticker>>
}