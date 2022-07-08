package com.mentalmachines.han.ui.main

import android.view.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowInsetsCompat.Type.navigationBars

class HanApp {
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

}