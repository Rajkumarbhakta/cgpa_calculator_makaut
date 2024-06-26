package com.rkbapps.makautsgpaygpacalculator.navigation

sealed class NavigationRoute(val route: String) {
    data object Home : NavigationRoute(route = "home")
    data object YearlyMarksCalculator : NavigationRoute(route = "yearly_marks_calculator")
    data object MidSemCalculator : NavigationRoute(route = "mid_sem_calculator")
    data object DgpaCalculator : NavigationRoute(route = "dgpa_calculator")
    data object SgpaYgpaPersentageCalculator : NavigationRoute(route = "sgpa_ygpa_percentage_calculator")
    data object AboutUs:NavigationRoute(route = "about_us")
    data object History:NavigationRoute(route = "history")
    data object YearlyMarksHistory:NavigationRoute(route = "yearly_marks_history")
    data object SgpaYgpaPercentageHistory:NavigationRoute(route = "sgpa_ygpa_percentage_history")
    data object MidSemHistory:NavigationRoute(route = "mid_sem_history")
    data object DgpaHistory:NavigationRoute(route = "dgpa_history")
}
