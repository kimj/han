package com.mentalmachines.han.ui

import android.view.WindowInsets
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat.Type.navigationBars
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mentalmachines.han.ui.theme.BlueTheme

class HanApp {
    enum class CourseTabs(
        @StringRes val title: Int,
        @DrawableRes val icon: Int,
        val route: String
    ) {
        MY_COURSES(R.string.my_courses, R.drawable.ic_grain, CoursesDestinations.MY_COURSES_ROUTE),
        FEATURED(R.string.featured, R.drawable.ic_featured, CoursesDestinations.FEATURED_ROUTE),
        SEARCH(R.string.search, R.drawable.ic_search, CoursesDestinations.SEARCH_COURSES_ROUTE)
    }

    /**
     * Destinations used in the ([HanApp]).
     */
    private object CoursesDestinations {
        const val FEATURED_ROUTE = "courses/featured"
        const val MY_COURSES_ROUTE = "courses/my"
        const val SEARCH_COURSES_ROUTE = "courses/search"
    }


    @Composable
    fun HanApp(finishActivity: () -> Unit) {
        BlueTheme {
            val tabs = remember { CourseTabs.values() }
            val navController = rememberNavController()
            Scaffold(
                backgroundColor = MaterialTheme.colors.primarySurface,
                bottomBar = { HanBottomBar(navController = navController, tabs) }
            ) { innerPaddingModifier ->
                NavGraph(
                    finishActivity = finishActivity,
                    navController = navController,
                    modifier = Modifier.padding(innerPaddingModifier)
                )
            }
        }
    }

    @Composable
    fun HanBottomBar(navController: NavController, tabs: Array<CourseTabs>) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
            ?: CourseTabs.FEATURED.route

        val routes = remember { CourseTabs.values().map { it.route } }
        if (currentRoute in routes) {
            BottomNavigation(
                Modifier.windowInsetsBottomHeight(
                    WindowInsets.navigationBars.add(WindowInsets(bottom = 56.dp))
                )
            ) {
                tabs.forEach { tab ->
                    BottomNavigationItem(
                        icon = { Icon(painterResource(tab.icon), contentDescription = null) },
                        label = { Text(stringResource(tab.title).uppercase(Locale.getDefault())) },
                        selected = currentRoute == tab.route,
                        onClick = {
                            if (tab.route != currentRoute) {
                                navController.navigate(tab.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        alwaysShowLabel = false,
                        selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = LocalContentColor.current,
                        modifier = Modifier.navigationBarsPadding()
                    )
                }
            }
        }
    }

    ToolTabs
}