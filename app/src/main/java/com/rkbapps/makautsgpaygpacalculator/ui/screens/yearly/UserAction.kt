package com.rkbapps.makautsgpaygpacalculator.ui.screens.yearly

sealed interface UserAction {
    data object OnSubmit: UserAction
    data object OnReset: UserAction
    data class OnUpdate(val input: YearlyMarksConverterState): UserAction
}