package com.curatedev.watchlist.presentation.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Ticker(
    @PrimaryKey
    val symbol: String
): Parcelable
