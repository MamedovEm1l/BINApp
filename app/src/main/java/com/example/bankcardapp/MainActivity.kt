package com.example.bankcardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bankcardapp.presentation.screens.BinInputScreen
import com.example.bankcardapp.presentation.screens.HistoryScreen
import com.example.bankcardapp.presentation.viewmodel.BinViewModel
import com.example.bankcardapp.ui.theme.BankCardAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val viewModel:BinViewModel = hiltViewModel()

            NavHost(navController = navController, startDestination = "binInput") {
                composable("binInput") {
                    BinInputScreen(
                        viewModel = viewModel,
                        onNavigateToHistory = { navController.navigate("history") }
                    )
                }
                composable("history") {
                    HistoryScreen(
                        viewModel = viewModel,
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
