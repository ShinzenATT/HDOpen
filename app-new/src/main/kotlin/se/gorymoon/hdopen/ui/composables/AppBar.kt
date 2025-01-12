package se.gorymoon.hdopen.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import se.gorymoon.hdopen.navigation.navigateSettings
import se.gorymoon.hdopen.ui.theme.HDOpenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(containerColor: Color, textColor: Color, nav: NavController, scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()){
    TopAppBar(
        title = { Text(
            text = "HDOpen"
        ) },
        colors = TopAppBarDefaults.topAppBarColors(
            scrolledContainerColor = containerColor,
            containerColor = containerColor,
            titleContentColor = textColor,
            actionIconContentColor = textColor,
            navigationIconContentColor = textColor
        ),
        scrollBehavior = scrollBehavior,
        actions = {
            /*IconButton(
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
            }*/

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

@OptIn(ExperimentalMaterial3Api::class)
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