package com.example.bankcardapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bankcardapp.presentation.viewmodel.BinViewModel

@Composable
fun HistoryScreen(viewModel: BinViewModel = hiltViewModel(), onNavigateBack: () -> Unit) {
    val history = viewModel.historyState.value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = onNavigateBack, modifier = Modifier.padding(bottom = 16.dp)) {
            Text("Back")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(history) { binEntity ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("BIN: ${binEntity.bin}")
                        Text("Info: ${binEntity.info}")
                    }
                }
            }
        }
    }
}