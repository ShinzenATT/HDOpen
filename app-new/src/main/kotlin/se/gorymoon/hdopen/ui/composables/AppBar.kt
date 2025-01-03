package se.gorymoon.hdopen.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import se.gorymoon.hdopen.navigation.navigateSettings
import se.gorymoon.hdopen.ui.theme.HDOpenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(containerColor: Color, textColor: Color, nav: NavController, ){
    TopAppBar(
        title = { Text(
            text = "HDOpen"
        ) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            titleContentColor = textColor,
            actionIconContentColor = textColor,
            navigationIconContentColor = textColor
        ),
        actions = {
            IconButton(
                onClick = {/* TODO open download page */},
                colors = IconButtonDefaults.filledTonalIconButtonColors(
                    containerColor = textColor,
                    contentColor = containerColor
                )
            ){
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = "Download Update"
                )
            }

            /*IconButton(
                onClick = { /* TODO open events*/}
            ){
                BadgedBox(
                    { Badge(Modifier.align(Alignment {size, space, layoutDirection ->  IntOffset(-15, 10) })) }
                    ) {
                    Icon(
                        imageVector = Icons.Default.Event,
                        contentDescription = "HD Events"
                    )
                }
            }*/

            IconButton(
                onClick = { nav.navigateSettings() }
            ){
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            }
        }
    )
}

@Preview
@Composable
private fun AppBarPreview(){
    HDOpenTheme {
        AppBar(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.onPrimary,
            rememberNavController()
        )
    }
}