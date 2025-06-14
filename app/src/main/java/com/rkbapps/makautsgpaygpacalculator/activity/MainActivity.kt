package com.rkbapps.makautsgpaygpacalculator.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.rkbapps.makautsgpaygpacalculator.navigation.NavManager
import com.rkbapps.makautsgpaygpacalculator.navigation.NavigationRoute
import com.rkbapps.makautsgpaygpacalculator.ui.theme.CGPACalculatorMAKAUTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CGPACalculatorMAKAUTTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val backStack = remember { mutableStateListOf<Any>(NavigationRoute.Home) }
                    NavManager(backStack)
                }
            }
        }
    }
}

