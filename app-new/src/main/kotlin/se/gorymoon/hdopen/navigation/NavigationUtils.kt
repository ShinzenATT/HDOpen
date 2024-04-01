package se.gorymoon.hdopen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.PopUpToBuilder
import se.gorymoon.hdopen.navigation.Route.Door
import se.gorymoon.hdopen.navigation.Route.Settings

fun NavController.navigate(route: Route, builder: (NavOptionsBuilder.() -> Unit)? = null){
    if(builder != null) {
        this.navigate(route.name, builder)
    } else {
        this.navigate(route.name)
    }
}

fun NavController.popStackAndNavigate(route: Route, builder: NavOptionsBuilder.() -> Unit = {})
    = navigate(route) {
        popUpTo(graph){ inclusive = true}
        builder()
    }

fun NavOptionsBuilder.popUpTo(graph: NavGraph, builder: PopUpToBuilder.() -> Unit = {})
    = popUpTo(graph.id, builder)

fun NavController.navigateDoor() = this.navigate(Door)

fun NavController.navigateSettings() = this.navigate(Settings)
