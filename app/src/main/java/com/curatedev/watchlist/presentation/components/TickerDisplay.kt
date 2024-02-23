package com.curatedev.watchlist.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import java.text.NumberFormat

@Composable
fun TickerDisplay(ticker: String, price: Float) {
    var lastPrice by rememberSaveable {
        mutableFloatStateOf(price)
    }
    var priceColor by remember {
        mutableStateOf(Color.White)
    }
    var savedPrice by rememberSaveable {
        mutableFloatStateOf(price)
    }
    lastPrice = savedPrice
    savedPrice = price
    LaunchedEffect(savedPrice) {
        priceColor = if (savedPrice > lastPrice) {
            Color.Green
        } else if (savedPrice < lastPrice) {
            Color.Red
        } else {
            Color.White
        }
        lastPrice = savedPrice
    }
    val currencyFormatter = NumberFormat.getCurrencyInstance()
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colors.background),

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(5.dp)
        ) {
            Text(ticker)
            Spacer(modifier = Modifier.width(5.dp))
            Text(currencyFormatter.format(savedPrice), color = priceColor)
        }
    }
}

@Preview
@Composable
private fun TickerDisplayPreview() {
    TickerDisplay(ticker = "NFLX", price = 570.56F)
}