package com.example.bankcardapp.data.repository

import android.util.Log
import com.example.bankcardapp.data.api.BinApi
import com.example.bankcardapp.data.database.BinDao
import com.example.bankcardapp.data.database.BinEntity
import com.example.bankcardapp.domain.model.BinInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BinRepository(private val api: BinApi, private val binDao: BinDao) {
    suspend fun getBinInfo(bin: String): BinInfo = withContext(Dispatchers.IO) {
        try {
            api.getBinInfo(bin)
        }catch (e: Exception) {
            Log.e("BinRep", "Error fetch bin: ${e.localizedMessage}")
            throw e
        }

    }

    suspend fun saveBinInfo(binEntity: BinEntity) = withContext(Dispatchers.IO) {
        binDao.insertBin(binEntity)
    }

    suspend fun getHistory(): List<BinEntity> = withContext(Dispatchers.IO) {
        binDao.getAllBins()
    }
}