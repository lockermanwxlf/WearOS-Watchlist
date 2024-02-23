package com.curatedev.finazon.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CandleUpdate(
    @Json(name = "p") val provider: String,
    @Json(name = "ch") val channel: String,
    @Json(name = "f") val frequency: String,
    @Json(name = "aggr") val aggregation: String,
    @Json(name = "s") val ticker: String,
    @Json(name = "t") val timestampSeconds: Long,
    @Json(name = "o") val open: Float,
    @Json(name = "h") val high: Float,
    @Json(name = "l") val low: Float,
    @Json(name = "c") val close: Float,

)
