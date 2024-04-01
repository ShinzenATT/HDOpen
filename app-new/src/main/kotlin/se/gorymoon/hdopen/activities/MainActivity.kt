package se.gorymoon.hdopen.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import se.gorymoon.hdopen.navigation.NavigationView
import se.gorymoon.hdopen.navigation.Route
import se.gorymoon.hdopen.ui.models.AdState
import se.gorymoon.hdopen.ui.theme.HDOpenTheme
import se.gorymoon.hdopen.ui.viewmodels.refreshDoorState
import se.gorymoon.hdopen.ui.viewmodels.startAdRotation

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        setContent {
            Log.d("Main Activity", "Recomposed activity")
            navController = rememberNavController()
            HDOpenTheme {
                NavigationView(navController, Route.Onboaring)
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