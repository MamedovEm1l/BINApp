package com.example.bankcardapp.presentation.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bankcardapp.presentation.composables.BinInfoDisplay
import com.example.bankcardapp.presentation.viewmodel.BinViewModel
@Composable
fun BinInputScreen(viewModel: BinViewModel = hiltViewModel(), onNavigateToHistory: () -> Unit) {
    val bin = remember { mutableStateOf("") }
    val info = viewModel.binInfoState.value

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
            placeholder = { Text("Например: 45737160") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.fetchBinInfo(bin.value) },
            enabled = bin.value.length == 8
        ) {
            Text("Search")
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
