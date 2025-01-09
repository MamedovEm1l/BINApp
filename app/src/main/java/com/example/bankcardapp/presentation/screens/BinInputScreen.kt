package com.example.bankcardapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bankcardapp.presentation.composables.BinInfoDisplay
import com.example.bankcardapp.presentation.viewmodel.BinViewModel

@Composable
fun BinInputScreen(viewModel: BinViewModel = hiltViewModel(), onNavigateToHistory: () -> Unit) {
    val bin = remember { mutableStateOf("") }
    val info = viewModel.binInfoState.value
    val error = viewModel.errorState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = bin.value,
            onValueChange = { bin.value = it },
            label = { Text("Enter BIN") },
            placeholder = { Text("Например: 45717360") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.fetchBinInfo(bin.value) },
            enabled = bin.value.length == 8
        ) {
            Text("Search")
        }

        error?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = it,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.h4
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        info?.let {
            BinInfoDisplay(binInfo = it)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateToHistory) {
            Text("View History")
        }
    }
}
