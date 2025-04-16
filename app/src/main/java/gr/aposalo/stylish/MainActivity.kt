package gr.aposalo.stylish

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import gr.aposalo.stylish.ui.theme.StylishTheme
import gr.aposalo.stylish.graph.RootNavigationGraph

@AndroidEntryPoint
class MainActivity :  ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StylishTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().background(Color.White),
                ) {
                    val navController = rememberNavController()
                    RootNavigationGraph(navController = navController)
                }
            }
        }
    }
}



