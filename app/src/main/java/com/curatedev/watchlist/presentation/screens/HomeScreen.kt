package com.curatedev.watchlist.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.curatedev.watchlist.R
import com.curatedev.watchlist.presentation.components.TickerDisplay
import com.curatedev.watchlist.presentation.viewmodels.HomeScreenViewModel
import kotlin.random.Random

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    val vm: HomeScreenViewModel = hiltViewModel()
    val tickers by vm.watchlist.collectAsState(initial = listOf())

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = {
                    navController.navigate("AddTicker") {
                        this.popUpTo("Home")
                        this.launchSingleTop = true
                    }
                },
                modifier = Modifier
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "Add Ticker")
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(tickers) {
                    var price by rememberSaveable {
                        mutableFloatStateOf(0F)
                    }
                    vm.watchTicker(it.symbol) {
                        price = it.close
                    }
                    TickerDisplay(ticker = it.symbol, price = price)
                }
            }
        }
    }
}