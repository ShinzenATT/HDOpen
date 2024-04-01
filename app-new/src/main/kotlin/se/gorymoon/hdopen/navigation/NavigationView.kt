package se.gorymoon.hdopen.navigation

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import se.gorymoon.hdopen.navigation.Route.*
import se.gorymoon.hdopen.ui.viewmodels.createSystemColorSetter
import se.gorymoon.hdopen.ui.views.HomeView
import se.gorymoon.hdopen.ui.views.OnboardingView
import se.gorymoon.hdopen.ui.views.SettingsView

@Composable
fun ComponentActivity.NavigationView(
    controller: NavHostController,
    start: Route
) = NavHost(
    navController = controller,
    startDestination = start.name
){

    composable(Door.name){
        label = Door.label
        HomeView(controller, createSystemColorSetter(window, LocalView.current))
    }
    composable(Settings.name){
        label = Settings.label
        SettingsView(controller, createSystemColorSetter(window, LocalView.current))
    }

    composable(Onboaring.name){
        label = Onboaring.label
        val screenWidth = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            windowManager.currentWindowMetrics.bounds.width()
        } else {
            windowManager.defaultDisplay.width
        }
        OnboardingView(controller, screenWidth, createSystemColorSetter(window, LocalView.current))
    }
}