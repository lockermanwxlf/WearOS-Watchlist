package com.curatedev.watchlist.presentation.screens

import android.text.Layout
import android.view.View
import android.widget.EditText
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.doOnTextChanged
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.room.util.appendPlaceholders
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.curatedev.watchlist.R
import com.curatedev.watchlist.presentation.viewmodels.AddTickerScreenViewModel

@Composable
fun AddTickerScreen() {
    val vm: AddTickerScreenViewModel = hiltViewModel()
    var text by rememberSaveable {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidView(factory = { viewContext ->
                EditText(viewContext).apply {
                    doOnTextChanged { t, _, _, _ -> text = t.toString() }
                    hint = "Enter Ticker"
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }
            })
            Button(
                onClick = {
                    vm.addTicker(text)
                },
                modifier = Modifier
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "Add Ticker")
            }
        }
    }
}