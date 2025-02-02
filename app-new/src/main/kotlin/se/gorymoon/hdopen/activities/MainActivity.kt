package se.gorymoon.hdopen.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import se.gorymoon.hdopen.navigation.NavigationView
import se.gorymoon.hdopen.navigation.Route
import se.gorymoon.hdopen.ui.models.AdState
import se.gorymoon.hdopen.ui.models.SettingsState
import se.gorymoon.hdopen.ui.models.SettingsState.awaitSettings
import se.gorymoon.hdopen.ui.theme.HDOpenTheme
import se.gorymoon.hdopen.ui.viewmodels.refreshDoorState
import se.gorymoon.hdopen.ui.viewmodels.startAdRotation

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        awaitSettings()
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        enableEdgeToEdge()
        setContent {
            Log.d("Main Activity", "Recomposed activity")
            navController = rememberNavController()
            HDOpenTheme {
                NavigationView(navController, if(SettingsState.value.onboardingCompleted) Route.Door else Route.Onboaring)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        AdState.cycleJob?.cancel()
    }

    override fun onResume() {
        super.onResume()
        if (AdState.cycleJob?.isCancelled == true){
            AdState.cycleJob = startAdRotation()
        }
        refreshDoorState()
    }
}