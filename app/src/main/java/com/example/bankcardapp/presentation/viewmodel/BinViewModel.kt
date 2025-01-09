package com.example.bankcardapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bankcardapp.data.database.BinEntity
import com.example.bankcardapp.data.repository.BinRepository
import com.example.bankcardapp.domain.model.BinInfo
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(
    private val repository: BinRepository
) : ViewModel() {

    val binInfoState = mutableStateOf<BinInfo?>(null)
    val historyState = mutableStateOf<List<BinEntity>>(emptyList())
init {
    try {
        viewModelScope.launch {
            loadHistory()
        }
    }catch (e: Exception) {
        Log.e("BinViewModel", "Error load history: ${e.localizedMessage}")
        throw e
    }

}
    fun fetchBinInfo(bin: String) {
        try {
            viewModelScope.launch {
                val info = repository.getBinInfo(bin)
                binInfoState.value = info
                repository.saveBinInfo(BinEntity(bin, info.toString()))
            }
        }catch (e: Exception) {
            Log.e("BinViewModel", "Error fetch bin: ${e.localizedMessage}")
            throw e
        }

    }

    private fun loadHistory() {
        viewModelScope.launch {
            historyState.value = repository.getHistory()
        }
    }

}
