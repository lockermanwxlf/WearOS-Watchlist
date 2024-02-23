package com.curatedev.finazon.requests

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Reset internal constructor(
    val event: String
){
    constructor() : this("reset")
}