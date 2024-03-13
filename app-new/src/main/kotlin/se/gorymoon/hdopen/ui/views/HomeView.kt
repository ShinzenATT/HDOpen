package se.gorymoon.hdopen.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import se.gorymoon.hdopen.R
import se.gorymoon.hdopen.ui.composables.DoorDisplay
import se.gorymoon.hdopen.ui.models.DoorState
import se.gorymoon.hdopen.ui.theme.HDOpenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView() {
    HDOpenTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(
                        text = "HDOpen"
                    ) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    actions = {
                        IconButton(
                            onClick = { /*TODO open settings*/ }
                        ){
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings"
                            )
                        }
                    }
                )
            },
            bottomBar = {
                Image(painter = painterResource(R.drawable.dumheter_iin), contentDescription = "Ad")
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {/* TODO Add action*/ DoorState.cycleState()},
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Icon(Icons.Filled.Refresh, "Refresh")
                }
            }
        ) {
            DoorDisplay(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    HomeView()
}