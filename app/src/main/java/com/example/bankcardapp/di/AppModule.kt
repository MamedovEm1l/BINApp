package com.example.bankcardapp.di

import android.app.Application
import com.example.bankcardapp.data.api.ApiClient
import com.example.bankcardapp.data.api.BinApi
import com.example.bankcardapp.data.database.BinDao
import com.example.bankcardapp.data.database.BinDatabase
import com.example.bankcardapp.data.repository.BinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBinApi(): BinApi = ApiClient.api

    @Provides
    @Singleton
    fun provideBinDatabase(application: Application): BinDatabase =
        androidx.room.Room.databaseBuilder(
            application,
            BinDatabase::class.java,
            "bin_database"
        ).build()

    @Provides
    @Singleton
    fun provideBinDao(database: BinDatabase): BinDao = database.binDao()

    @Provides
    @Singleton
    fun provideBinRepository(api: BinApi, binDao: BinDao): BinRepository = BinRepository(api, binDao)
}