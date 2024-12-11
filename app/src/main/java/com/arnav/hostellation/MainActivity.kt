package com.arnav.hostellation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.arnav.hostellation.ui.theme.HostellationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            HostellationTheme {
                val navController = rememberNavController()
                NavigationRoot(navHostController = navController)
            }
        }
    }
}