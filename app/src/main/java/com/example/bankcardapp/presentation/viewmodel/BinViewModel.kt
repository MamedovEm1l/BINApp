package com.example.bankcardapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankcardapp.data.database.BinEntity
import com.example.bankcardapp.data.repository.BinRepository
import com.example.bankcardapp.domain.model.BinInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(
    private val repository: BinRepository
) : ViewModel() {

    val binInfoState = mutableStateOf<BinInfo?>(null)
    val historyState = mutableStateOf<List<BinEntity>>(emptyList())
    val errorState = mutableStateOf<String?>(null)

    init {
        viewModelScope.launch {
            loadHistory()
        }
    }

    fun fetchBinInfo(bin: String) {
        viewModelScope.launch {
            try {
                val info = repository.getBinInfo(bin)
                binInfoState.value = info
                repository.saveBinInfo(BinEntity(bin = bin, info = info.toString()))
                errorState.value = null
            } catch (e: IOException) {
                binInfoState.value = null
                errorState.value = e.localizedMessage
            }
        }
    }


    private fun loadHistory() {
        viewModelScope.launch {
            historyState.value = repository.getHistory()
        }
    }
}
