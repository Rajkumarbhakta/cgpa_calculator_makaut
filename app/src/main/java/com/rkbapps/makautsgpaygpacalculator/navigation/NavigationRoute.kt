package com.rkbapps.makautsgpaygpacalculator.navigation

sealed class NavigationRoute(val route: String) {
    data object Home : NavigationRoute(route = "home")
    data object YearlyMarksCalculator : NavigationRoute(route = "yearly_marks_calculator")
    data object MidSemCalculator : NavigationRoute(route = "mid_sem_calculator")
    data object DgpaCalculator : NavigationRoute(route = "dgpa_calculator")
    data object SgpaYgpaPersentageCalculator : NavigationRoute(route = "sgpa_ygpa_percentage_calculator")
    data object AboutUs:NavigationRoute(route = "about_us")
    data object History:NavigationRoute(route = "history")

}
