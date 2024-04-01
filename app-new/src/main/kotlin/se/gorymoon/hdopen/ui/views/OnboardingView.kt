package se.gorymoon.hdopen.ui.views

import androidx.activity.BackEventCompat
import androidx.activity.compose.PredictiveBackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import se.gorymoon.hdopen.navigation.Route
import se.gorymoon.hdopen.navigation.popStackAndNavigate
import se.gorymoon.hdopen.ui.composables.onboarding.*
import se.gorymoon.hdopen.ui.theme.HDOpenTheme
import se.gorymoon.hdopen.ui.theme.theme
import se.gorymoon.hdopen.ui.viewmodels.SystemColorSetter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingView(nav: NavController, screenWidth: Int, setSystemColor: SystemColorSetter){
    val page = rememberPagerState{ 5 }
    val dispatcher = rememberCoroutineScope()

    PredictiveBackHandler(page.canScrollBackward) { flow ->
        val targetPage = page.currentPage - 1
        try {
            var lastEvent: BackEventCompat? = null
            flow.collect {
                val delta = if(lastEvent != null) it.progress - lastEvent!!.progress else 0f
                page.scroll {
                    scrollBy(delta * screenWidth * -1)
                }
                lastEvent = it
            }
            page.animateScrollToPage(targetPage)
        } catch(e: CancellationException) {
            page.animateScrollToPage(targetPage + 1)
        }
    }

    setSystemColor(theme().surface, theme().surfaceColorAtElevation(BottomAppBarDefaults.ContainerElevation))

    Scaffold(
        containerColor = theme().surface,
        contentColor = theme().onSurface,
        bottomBar = { BottomAppBar {
            Row {
                IconButton(
                    enabled = page.canScrollBackward,
                    onClick = {
                        dispatcher.launch{
                            page.animateScrollToPage(page.currentPage - 1)
                        }
                  },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ){
                    Icon(Icons.Default.ArrowBack, contentDescription = "Go back")
                }
                LinearProgressIndicator(
                    progress = (page.currentPage + page.currentPageOffsetFraction) / (page.pageCount - 1f),
                    modifier = Modifier.align(Alignment.CenterVertically).weight(1f)
                )
                FloatingActionButton(
                    modifier = Modifier.padding(start = 12.dp, end = 6.dp),
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                    onClick =  {
                        if(!page.canScrollForward){
                            nav.popStackAndNavigate(Route.Door)
                        } else {
                            dispatcher.launch {
                                page.animateScrollToPage(page.currentPage + 1)
                            }
                        }
                    }
                ){
                    if(!page.canScrollForward){
                        Icon(Icons.Default.Check, contentDescription = "Finish")
                    } else {
                        Icon(Icons.Default.ArrowForward, contentDescription = "Next")
                    }
                }
            }
        }}
    ) {
        Surface(
            Modifier.padding(it).fillMaxSize(),
            color = theme().surface,
            contentColor = theme().onSurface) {
            HorizontalPager(page, Modifier.fillMaxSize()){ currentPage ->
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().padding(30.dp)) {
                    when (currentPage) {
                        0 -> TitleOnboarding()
                        1 -> ThemeOnboarding()
                        2 -> DoorOnboarding()
                        3 -> NotificationOnboarding()
                        4 -> FinishOnboarding(nav)
                        else -> Text("Oops, this page $currentPage should not exist.")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun OnboardingPreview(){
    HDOpenTheme(darkTheme = true) {
        OnboardingView(rememberNavController(), 0) { _, _ -> }
    }
}
