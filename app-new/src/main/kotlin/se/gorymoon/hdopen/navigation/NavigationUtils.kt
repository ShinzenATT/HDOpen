package se.gorymoon.hdopen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import se.gorymoon.hdopen.navigation.Route.Door

fun NavController.navigate(route: Route, options: NavOptions? = null, extras: Navigator.Extras? = null)
    = this.navigate(route.name, options, extras)

fun NavController.navigateDoor() = this.navigate(Door)
