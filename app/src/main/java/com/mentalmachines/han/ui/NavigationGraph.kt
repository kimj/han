package com.mentalmachines.han.ui

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
import com.mentalmachines.han.ui.dictionary.DictionaryScreen
import com.mentalmachines.han.ui.flash_cards.FlashCardScreen
import com.mentalmachines.han.ui.grammar.GrammarScreen
import com.mentalmachines.han.ui.home.HomeScreen
import com.mentalmachines.han.ui.home.HomeScreenActions


sealed class Screen(val name: String, val route: String) {
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
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {

        composable(Screen.Home.route) {
            HomeScreen(onAction = actions::navigateFromHome)
        }

        composable(Screen.Grammar.route) {
            GrammarScreen()
        }

        composable(Screen.Dictionary.route) {
            DictionaryScreen()
        }

        composable(Screen.VerbConjugator.route) {
            VerbConjugationScreen()
        }

        composable(Screen.FlashCard.route) {
            FlashCardScreen()
        }
    }
}

class NavActions(private val navController: NavController) {
    fun navigateFromHome(actions: HomeScreenActions) {
        when (actions) {
            HomeScreenActions.Back -> {
                navController.navigate(Screen.Home.name)
            }
            else -> {}
        }
    }

    fun navigateToDictionary(actions: HomeScreenActions) {
        when (actions) {
            HomeScreenActions.Dictionary -> {
                navController.navigate(Screen.Dictionary.name)
            }
            HomeScreenActions.Back -> {
                navController.navigate(Screen.Home.name)
            }
        }
    }


    fun navigateFromDetails(actions: DetailScreenActions) {
        when (actions) {
            DetailScreenActions.Back -> navController.popBackStack()
        }
    }
}
