package com.curatedev.watchlist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.curatedev.finazon.CandleUpdateListener
import com.curatedev.finazon.Finazon
import com.curatedev.watchlist.presentation.data.Ticker
import com.curatedev.watchlist.presentation.data.TickersDao
import com.curatedev.watchlist.presentation.data.TickersDatabase
import com.curatedev.watchlist.presentation.repositories.TickersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val tickersRepository: TickersRepository,
    private val finazon: Finazon
): ViewModel() {
    val watchlist = tickersRepository.tickersFlow
    init {
        viewModelScope.launch(Dispatchers.IO) {
            while(true) {
                finazon.webSocket.heartbeat()
                Thread.sleep(1000 * 30)
            }
        }

    }


    fun watchTicker(ticker: String, onCandleUpdateListener: CandleUpdateListener) = finazon.webSocket.subscribeToTicker(ticker, onCandleUpdateListener)

}