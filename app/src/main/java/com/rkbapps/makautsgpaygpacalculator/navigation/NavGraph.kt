package com.rkbapps.makautsgpaygpacalculator.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rkbapps.makautsgpaygpacalculator.screens.about.AboutScreen
import com.rkbapps.makautsgpaygpacalculator.screens.dgpa.DgpaCalculatorScreen
import com.rkbapps.makautsgpaygpacalculator.screens.history.HistoryScreen
import com.rkbapps.makautsgpaygpacalculator.screens.history.midsem.MidSemCalculationHistoryScreen
import com.rkbapps.makautsgpaygpacalculator.screens.history.yearly.YearlyMarksHistoryScreen
import com.rkbapps.makautsgpaygpacalculator.screens.history.ygpa.SgpaYgpaHistoryScreen
import com.rkbapps.makautsgpaygpacalculator.screens.home.HomeScreen
import com.rkbapps.makautsgpaygpacalculator.screens.midsem.MidSemCalculatorScreen
import com.rkbapps.makautsgpaygpacalculator.screens.ygpa.SgpaYgpaPersentageCalculatorScreen
import com.rkbapps.makautsgpaygpacalculator.screens.yearly.YearlyMarksConverterScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController,
        startDestination = NavigationRoute.Home.route,
        ) {
        composable(route = NavigationRoute.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = NavigationRoute.YearlyMarksCalculator.route,
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
            YearlyMarksConverterScreen(navController = navController)
        }
        composable(route = NavigationRoute.DgpaCalculator.route,

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
            DgpaCalculatorScreen(navController = navController)
        }
        composable(route = NavigationRoute.MidSemCalculator.route,

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
            MidSemCalculatorScreen(navController = navController)
        }
        composable(route = NavigationRoute.SgpaYgpaPersentageCalculator.route,
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
            SgpaYgpaPersentageCalculatorScreen(navController = navController)
        }
        composable(route = NavigationRoute.AboutUs.route,
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
            ){
            AboutScreen()
        }

        composable(route = NavigationRoute.History.route,
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
        ){
            HistoryScreen(navController)
        }

        composable(route=NavigationRoute.YearlyMarksHistory.route){
            YearlyMarksHistoryScreen(navController)
        }
        
        composable(route=NavigationRoute.SgpaYgpaPercentageHistory.route){
            SgpaYgpaHistoryScreen(navController = navController)
        }
        composable(route=NavigationRoute.MidSemHistory.route){
            MidSemCalculationHistoryScreen(navController = navController)
        }

    }
}


