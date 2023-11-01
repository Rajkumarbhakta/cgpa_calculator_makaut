package com.rkbapps.makautsgpaygpacalculator.navigation

sealed class NavigationRoute(val route: String) {
    object Home : NavigationRoute(route = "home")
    object YearlyMarksCalculator : NavigationRoute(route = "yearly_marks_calculator")
    object MidSemCalculator : NavigationRoute(route = "mid_sem_calculator")
    object DgpaCalculator : NavigationRoute(route = "dgpa_calculator")
    object SgpaYgpaPersentageCalculator : NavigationRoute(route = "sgpa_ygpa_percentage_calculator")
}
