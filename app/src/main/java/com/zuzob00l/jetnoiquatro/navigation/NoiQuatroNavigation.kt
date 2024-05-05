package com.zuzob00l.jetnoiquatro.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zuzob00l.jetnoiquatro.ui.screens.LoginScreen
import com.zuzob00l.jetnoiquatro.ui.screens.MapScreen
import com.zuzob00l.jetnoiquatro.ui.screens.StartScreen
@ExperimentalMaterial3Api
@Composable
fun NoiQuatroNavigation()
{
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NoiQuatroScreens.StartScreen.name)
    {
       composable(route = NoiQuatroScreens.StartScreen.name) {
           StartScreen(navController = navController)
       }

        composable(route = NoiQuatroScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }

        composable(route = NoiQuatroScreens.HomeScreen.name) {
            LoginScreen(navController = navController)
        }
        composable(route = NoiQuatroScreens.MapScreen.name) {

        }
    }
}
