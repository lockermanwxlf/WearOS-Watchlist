package com.curatedev.watchlist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curatedev.watchlist.presentation.data.Ticker
import com.curatedev.watchlist.presentation.repositories.TickersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTickerScreenViewModel @Inject constructor(
    private val tickersRepository: TickersRepository
): ViewModel() {
    fun addTicker(ticker: String) = viewModelScope.launch(Dispatchers.IO) {
        tickersRepository.addTicker(Ticker(ticker))
    }
}