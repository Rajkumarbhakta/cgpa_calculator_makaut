package com.rkbapps.makautsgpaygpacalculator.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rkbapps.makautsgpaygpacalculator.ui.screens.about.AboutScreen
import com.rkbapps.makautsgpaygpacalculator.ui.screens.dgpa.DgpaCalculatorScreen
import com.rkbapps.makautsgpaygpacalculator.ui.screens.history.HistoryScreen
import com.rkbapps.makautsgpaygpacalculator.ui.screens.history.dgpa.DgpaMarksHistoryScreen
import com.rkbapps.makautsgpaygpacalculator.ui.screens.history.midsem.MidSemCalculationHistoryScreen
import com.rkbapps.makautsgpaygpacalculator.ui.screens.history.yearly.YearlyMarksHistoryScreen
import com.rkbapps.makautsgpaygpacalculator.ui.screens.history.ygpa.SgpaYgpaHistoryScreen
import com.rkbapps.makautsgpaygpacalculator.ui.screens.home.HomeScreen
import com.rkbapps.makautsgpaygpacalculator.ui.screens.midsem.MidSemCalculatorScreen
import com.rkbapps.makautsgpaygpacalculator.ui.screens.yearly.YearlyMarksConverterScreen
import com.rkbapps.makautsgpaygpacalculator.ui.screens.ygpa.SgpaYgpaPercentageCalculatorScreen


/**
 * This function is used to create the navigation graph for the app.
 * It contains all the composable functions for the app screens.
 *
 * @param navController The NavHostController used to navigate between screens.
 */
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Home,
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        }
    ) {
        mainNavGraph(navController = navController)
        historyNavGraph(navController = navController)
    }
}


/**
 * This function is used to create the navigation graph for the history screens.
 * It contains all the composable functions for the history screens.
 *
 * @param navController The NavHostController used to navigate between screens.
 */
fun NavGraphBuilder.historyNavGraph(navController: NavHostController) {
    composable<NavigationRoute.History>() {
        HistoryScreen(navController)
    }
    composable<NavigationRoute.YearlyMarksHistory>() {
        YearlyMarksHistoryScreen(navController)
    }
    composable<NavigationRoute.SgpaYgpaPercentageHistory>() {
        SgpaYgpaHistoryScreen(navController = navController)
    }
    composable<NavigationRoute.MidSemHistory>() {
        MidSemCalculationHistoryScreen(navController = navController)
    }
    composable<NavigationRoute.DgpaHistory>() {
        DgpaMarksHistoryScreen(navController = navController)
    }
}


/**
 * This function is used to create the main navigation graph for the app.
 * It contains all the composable functions for the main screens.
 *
 * @param navController The NavHostController used to navigate between screens.
 */
fun NavGraphBuilder.mainNavGraph(navController:NavHostController){
    composable<NavigationRoute.Home>() {
        HomeScreen(navController = navController)
    }
    composable<NavigationRoute.YearlyMarksCalculator>() {
        YearlyMarksConverterScreen(navController = navController)
    }
    composable<NavigationRoute.DgpaCalculator>() {
        DgpaCalculatorScreen(navController = navController)
    }
    composable<NavigationRoute.MidSemCalculator>() {
        MidSemCalculatorScreen(navController = navController)
    }
    composable<NavigationRoute.SgpaYgpaPercentageCalculator>() {
        SgpaYgpaPercentageCalculatorScreen(navController = navController)
    }
    composable<NavigationRoute.AboutUs>() {
        AboutScreen()
    }
}


