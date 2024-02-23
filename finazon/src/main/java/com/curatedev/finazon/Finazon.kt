package com.curatedev.finazon

import okhttp3.OkHttpClient

class Finazon() {
    private val client: OkHttpClient = OkHttpClient()
    val webSocket = WebSocket(client)
}