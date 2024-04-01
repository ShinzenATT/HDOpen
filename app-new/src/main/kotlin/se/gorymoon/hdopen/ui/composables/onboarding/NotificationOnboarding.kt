package se.gorymoon.hdopen.ui.composables.onboarding

import android.Manifest
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import se.gorymoon.hdopen.ui.composables.BulletText

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationOnboarding(){
    val context = LocalView.current.context
    val requestPermission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {}

    Column(Modifier.fillMaxSize()) {
        Text(
            "Notifications",
            fontSize = 5.em,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
        )
        Text(
            "The app sends various notifications regarding door and events which can be adjusted in the notification category settings.",
            modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 20.dp),
            textAlign = TextAlign.Justify
        )

        Text(
            "The following notifications are sent when:",
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 5.dp)
        )
        BulletText("Chassit opens/closes.", modifier = Modifier.padding(vertical = 5.dp))
        BulletText("An event is added/starts.", modifier = Modifier.padding(vertical = 5.dp))
        BulletText("The app has a new update.", modifier = Modifier.padding(vertical = 5.dp))

        Spacer(Modifier.weight(1f))

        Button(
            onClick = {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    requestPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
                 val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Open Notification Settings")
        }
    }
}