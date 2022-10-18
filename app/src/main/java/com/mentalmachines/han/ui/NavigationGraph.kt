package com.mentalmachines.han.ui

import android.window.SplashScreen
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.mentalmachines.han.ui.conjugator.DetailScreenActions
import com.mentalmachines.han.ui.conjugator.VerbConjugationScreen
import com.mentalmachines.han.ui.flash_cards.FlashCardScreen


sealed class Screen(val name: String, val route: String) {
    object Splash : Screen("splash", "splash")
    object Home : Screen("home", "home")
    object VerbConjugator : Screen("verbconjugator", "verbconjugator")
    object Grammar : Screen("grammar", "grammar")
    object Dictionary : Screen("dictionary", "dictionary")
    object FlashCard : Screen("flashcard", "flashcard")
}

@ExperimentalAnimationApi
@Composable
fun HanNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    actions: NavActions = remember(navController) {
        NavActions(navController)
    }
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onAction = actions::navigateToHome)
        }

        composable(Screen.Home.route) {
            HomeScreen(onAction = actions::navigateFromHome, navController = navController)
        }

        composable(Screen.Grammar.route) {
            GrammarScreen(onAction = actions::navigateFromDetails)
        }

        composable(Screen.Dictionary.route) {
            DictionaryScreen(onAction = actions::navigateFromDetails)
        }

        composable(Screen.VerbConjugator.route) {
            VerbConjugationScreen(onAction = actions::navigateFromDetails)
        }

        composable(Screen.FlashCard.route) {
            FlashCardScreen(onAction = actions::navigateFromDetails)
        }
    }
}

class NavActions(private val navController: NavController) {
    fun navigateToHome(_A: SplashScreenActions) {
        navController.navigate(Screen.Home.name) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }

    fun navigateFromHome(actions: HomeScreenActions) {
        when (actions) {
            HomeScreenActions.Details -> {
                navController.navigate(Screen.Detail.name)
            }
        }
    }

    fun navigateFromDetails(actions: DetailScreenActions) {
        when (actions) {
            DetailScreenActions.Back -> navController.popBackStack()
        }
    }
}
