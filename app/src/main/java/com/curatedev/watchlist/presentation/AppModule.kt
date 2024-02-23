package com.curatedev.watchlist.presentation

import android.content.Context
import androidx.room.Room
import com.curatedev.finazon.Finazon
import com.curatedev.watchlist.presentation.data.TickersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context, TickersDatabase::class.java, "tickersdatabase").build()

    @Provides
    fun getTickersDao(tickersDatabase: TickersDatabase) = tickersDatabase.tickersDao()

    @Provides
    @Singleton
    fun getFinazon() = Finazon()
}