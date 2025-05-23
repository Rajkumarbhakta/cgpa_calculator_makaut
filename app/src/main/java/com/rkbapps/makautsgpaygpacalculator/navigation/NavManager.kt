package com.rkbapps.makautsgpaygpacalculator.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
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

/**
 * Manages the navigation flow of the application.
 *
 * This Composable function sets up the `NavDisplay` which is responsible for
 * displaying the current screen based on the `backStack`. It defines the
 * entry decorators, transition animations, and the entry provider for the
 * navigation graph.
 *
 * @param backStack A `SnapshotStateList` representing the navigation back stack.
 *                  Changes to this list will trigger recomposition and navigation.
 */
@Composable
fun NavManager(
    backStack: SnapshotStateList<Any>,
) {
    NavDisplay(
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = backStack, onBack = { backStack.removeLastOrNull() },
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { -it })
        },
        popTransitionSpec = {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { it })
        },
        entryProvider = entryProvider {
            mainNavDisplay(backStack)
            historyNavGraph(backStack)
        })
}

/**
 * Defines the navigation entries for the main application flow.
 *
 * This function is an extension on `EntryProviderBuilder` and is used within the `NavManager`
 * to declare the different screens accessible from the main navigation.
 *
 * Each `entry` block defines a route and the Composable function that should be displayed
 * when that route is active. The `backStack` is passed to each screen to enable navigation
 * actions like going back or navigating to other screens.
 *
 * @param backStack The `SnapshotStateList` representing the current navigation back stack.
 *                  This is used by the screens to manage navigation.
 */
fun EntryProviderBuilder<Any>.mainNavDisplay(backStack: SnapshotStateList<Any>) {
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


/**
 * Defines the navigation graph for the history screens.
 *
 * This function is an extension function for `EntryProviderBuilder` and is used to
 * register the different history screens with the navigation system.
 * Each entry maps a `NavigationRoute` to its corresponding Composable screen.
 *
 * @param backStack The snapshot state list representing the navigation back stack.
 *                  This is passed to each history screen to enable navigation.
 */
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
