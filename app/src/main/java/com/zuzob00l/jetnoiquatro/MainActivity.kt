package com.zuzob00l.jetnoiquatro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zuzob00l.jetnoiquatro.navigation.NoiQuatroNavigation
import com.zuzob00l.jetnoiquatro.ui.screens.LoginScreen
import com.zuzob00l.jetnoiquatro.ui.screens.StartScreen
import com.zuzob00l.jetnoiquatro.ui.theme.JetNoiQuatroTheme
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNoiQuatroTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NoiQuatroApp()
                }
            }
        }
    }
}
@ExperimentalMaterial3Api
@Composable
fun NoiQuatroApp()
{
    NoiQuatroNavigation()
}
