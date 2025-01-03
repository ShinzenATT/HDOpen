package se.gorymoon.hdopen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.PopUpToBuilder
import se.gorymoon.hdopen.navigation.Route.Door
import se.gorymoon.hdopen.navigation.Route.Settings

fun NavController.navigateRoute(route: Route, builder: (NavOptionsBuilder.() -> Unit)? = null){
    if(builder != null) {
        this.navigate(route.name, builder)
    } else {
        this.navigate(route.name)
    }
}

fun NavController.popStackAndNavigate(route: Route, builder: NavOptionsBuilder.() -> Unit = {})
    = navigateRoute(route) {
        popUpToGraph(graph){ inclusive = true}
        builder()
    }

fun NavOptionsBuilder.popUpToGraph(graph: NavGraph, builder: PopUpToBuilder.() -> Unit = {})
    = popUpTo(graph.id, builder)

fun NavController.navigateDoor() = this.navigateRoute(Door)

fun NavController.navigateSettings() = this.navigateRoute(Settings)
