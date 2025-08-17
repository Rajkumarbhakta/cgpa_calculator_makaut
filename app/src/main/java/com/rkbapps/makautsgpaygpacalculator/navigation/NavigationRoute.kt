package com.rkbapps.makautsgpaygpacalculator.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class NavigationRoute() : NavKey {
    @Serializable
    data object Home : NavigationRoute()

    @Serializable
    data object YearlyMarksCalculator : NavigationRoute()

    @Serializable
    data object MidSemCalculator : NavigationRoute()

    @Serializable
    data object DgpaCalculator : NavigationRoute()

    @Serializable
    data object SgpaYgpaPercentageCalculator : NavigationRoute()

    @Serializable
    data object AboutUs : NavigationRoute()

    @Serializable
    data object History : NavigationRoute()

    @Serializable
    data object YearlyMarksHistory : NavigationRoute()

    @Serializable
    data object SgpaYgpaPercentageHistory : NavigationRoute()

    @Serializable
    data object MidSemHistory : NavigationRoute()

    @Serializable
    data object DgpaHistory : NavigationRoute()
}
