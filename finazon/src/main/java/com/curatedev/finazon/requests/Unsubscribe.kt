package com.curatedev.finazon.requests

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Unsubscribe internal constructor(
    val event: String,
    val dataset: String,
    val tickers: List<String>,
    val channel: String,
    val frequency: String,
    val aggregation: String
) {
    constructor(ticker: String, frequency: String) : this("unsubscribe", "sip_non_pro", listOf(ticker), "bars", frequency, "1m")
}