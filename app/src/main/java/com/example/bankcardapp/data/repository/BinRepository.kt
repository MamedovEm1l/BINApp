package com.example.bankcardapp.data.repository

import android.util.Log
import com.example.bankcardapp.data.api.BinApi
import com.example.bankcardapp.data.database.BinDao
import com.example.bankcardapp.data.database.BinEntity
import com.example.bankcardapp.domain.model.BinInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import retrofit2.HttpException
import java.io.IOException

class BinRepository(private val api: BinApi, private val binDao: BinDao) {
    suspend fun getBinInfo(bin: String): BinInfo = withContext(Dispatchers.IO) {
        try {
            api.getBinInfo(bin)
        } catch (e: HttpException) {
            val errorMessage = when (e.code()) {
                400 -> "Bad Request: Invalid BIN format."
                404 -> "Not Found: BIN not found in the database."
                500 -> "Server Error: Please try again later."
                else -> "Unexpected error: ${e.message()}"
            }
            throw IOException(errorMessage, e)
        } catch (e: IOException) {
            throw IOException("Network error: Please check your connection and try again.", e)
        } catch (e: Exception) {
            throw IOException("Unexpected error occurred.", e)
        }
    }

    suspend fun saveBinInfo(binEntity: BinEntity) = withContext(Dispatchers.IO) {
        binDao.insertBin(binEntity)
    }

    suspend fun getHistory(): List<BinEntity> = withContext(Dispatchers.IO) {
        binDao.getAllBins()
    }
}
