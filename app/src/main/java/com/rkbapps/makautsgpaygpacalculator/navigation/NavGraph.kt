package com.rkbapps.makautsgpaygpacalculator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rkbapps.makautsgpaygpacalculator.components.DgpaCalculatorScreen
import com.rkbapps.makautsgpaygpacalculator.components.HomeScreen
import com.rkbapps.makautsgpaygpacalculator.components.MidSemCalculatorScreen
import com.rkbapps.makautsgpaygpacalculator.components.SgpaYgpaPersentageCalculatorScreen
import com.rkbapps.makautsgpaygpacalculator.components.YearlyMarksConverterScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavigationRoute.Home.route) {
        composable(route = NavigationRoute.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = NavigationRoute.YearlyMarksCalculator.route) {
            YearlyMarksConverterScreen(navController = navController)
        }
        composable(route = NavigationRoute.DgpaCalculator.route) {
            DgpaCalculatorScreen(navController = navController)
        }
        composable(route = NavigationRoute.MidSemCalculator.route) {
            MidSemCalculatorScreen(navController = navController)
        }
        composable(route = NavigationRoute.SgpaYgpaPersentageCalculator.route) {
            SgpaYgpaPersentageCalculatorScreen(navController = navController)
        }

    }
}