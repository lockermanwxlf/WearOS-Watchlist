package com.curatedev.watchlist.presentation.repositories

import com.curatedev.watchlist.presentation.data.Ticker
import com.curatedev.watchlist.presentation.data.TickersDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TickersRepository @Inject constructor(
    private val tickersDao: TickersDao
) {
    val tickersFlow = tickersDao.getAll()

    suspend fun addTicker(ticker: Ticker) = withContext(Dispatchers.IO) {
        tickersDao.insert(ticker)
    }
}