package se.gorymoon.hdopen.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import se.gorymoon.hdopen.navigation.Route.Door
import se.gorymoon.hdopen.ui.views.HomeView

@Composable
fun ComponentActivity.NavigationView(controller: NavHostController,start: Route)
    = NavHost(
        navController = controller,
        startDestination = start.name
){
    composable(Door.name){
        HomeView(controller, window)
    }
}