package com.rkbapps.makautsgpaygpacalculator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.rkbapps.makautsgpaygpacalculator.navigation.NavigationRoute.Home
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

@Composable
fun NavManager(
    backStack:SnapshotStateList<Any>,
) {
    NavDisplay(
        backStack = backStack, onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            mainNavDisplay(backStack)
            historyNavGraph(backStack)
        })
}

fun EntryProviderBuilder<Any>.mainNavDisplay(backStack: SnapshotStateList<Any>){
    entry<Home> {
        HomeScreen(backStack = backStack)
    }
    entry<NavigationRoute.YearlyMarksCalculator>() {
        YearlyMarksConverterScreen(backStack = backStack)
    }
    entry<NavigationRoute.DgpaCalculator>() {
        DgpaCalculatorScreen(backStack = backStack)
    }
    entry<NavigationRoute.MidSemCalculator>() {
        MidSemCalculatorScreen(backStack = backStack)
    }
    entry<NavigationRoute.SgpaYgpaPercentageCalculator>() {
        SgpaYgpaPercentageCalculatorScreen(backStack = backStack)
    }
    entry<NavigationRoute.AboutUs>() {
        AboutScreen()
    }
}


fun EntryProviderBuilder<Any>.historyNavGraph(backStack: SnapshotStateList<Any>) {
    entry<NavigationRoute.History>() {
        HistoryScreen(backStack = backStack)
    }
    entry<NavigationRoute.YearlyMarksHistory>() {
        YearlyMarksHistoryScreen(backStack = backStack)
    }
    entry<NavigationRoute.SgpaYgpaPercentageHistory>() {
        SgpaYgpaHistoryScreen(backStack = backStack)
    }
    entry<NavigationRoute.MidSemHistory>() {
        MidSemCalculationHistoryScreen(backStack = backStack)
    }
    entry<NavigationRoute.DgpaHistory>() {
        DgpaMarksHistoryScreen(backStack = backStack)
    }
}
