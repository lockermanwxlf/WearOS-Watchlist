package com.curatedev.finazon

import com.curatedev.finazon.requests.Heartbeat
import com.curatedev.finazon.requests.Reset
import com.curatedev.finazon.requests.Subscribe
import com.curatedev.finazon.requests.Unsubscribe
import com.curatedev.finazon.responses.CandleUpdate
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

typealias CandleUpdateListener = (CandleUpdate) -> Unit

class WebSocket(client: OkHttpClient) {
    companion object {
        private val moshi = Moshi.Builder().build()
    }
    @Suppress("unused")
    private object RequestAdapters {
        val heartbeat = moshi.adapter(Heartbeat::class.java)!!
        val reset = moshi.adapter(Reset::class.java)!!
        val subscribe = moshi.adapter(Subscribe::class.java)!!
        val unsubscribe = moshi.adapter(Unsubscribe::class.java)!!
    }
    @Suppress("unused")
    private object ResponseAdapters {
        val candleUpdate = moshi.adapter(CandleUpdate::class.java)!!
    }

    val authRequest = Request.Builder()
        .url("wss://ws.finazon.io/v1?apikey=${BuildConfig.FINAZON_API_KEY}")
        .build()
    val candleUpdateListeners = hashMapOf<String, CandleUpdateListener>()
    val webSocket = client.newWebSocket(request = authRequest, listener = Listener(candleUpdateListeners))

    fun subscribeToTicker(ticker: String, onCandleUpdate: CandleUpdateListener) {
        if (!candleUpdateListeners.containsKey(ticker)) {
            webSocket.send(RequestAdapters.subscribe.toJson(Subscribe(ticker = ticker, frequency = "1s")))
        }
        candleUpdateListeners[ticker] = onCandleUpdate
    }

    fun heartbeat() {
        webSocket.send(RequestAdapters.heartbeat.toJson(Heartbeat()))
    }

    class Listener(
        private val candleUpdateListeners: Map<String, CandleUpdateListener>
    ): WebSocketListener() {
        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            println("WS ERR: ${t.localizedMessage}")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            try {
                ResponseAdapters.candleUpdate.fromJson(text)?.let { candleUpdate ->
                    candleUpdateListeners[candleUpdate.ticker]?.let {listener ->
                        listener(candleUpdate)
                    }
                }
            } catch (_: JsonDataException) {
                println("MISC MSG: $text")
            }
        }


    }
}