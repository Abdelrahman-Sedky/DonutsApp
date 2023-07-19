package com.example.donuts.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.donuts.ui.screens.TransparentSystemBars
import com.example.donuts.ui.screens.details.DetailsScreen
import com.example.donuts.ui.theme.DonutsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            TransparentSystemBars()
            DonutsTheme {
                DonutsNavGraph()
            }
        }
    }
}