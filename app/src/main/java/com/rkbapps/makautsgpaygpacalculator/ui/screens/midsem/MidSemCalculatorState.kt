package com.rkbapps.makautsgpaygpacalculator.ui.screens.midsem

data class MidSemCalculatorState(
    val selectedOption: Options = Options.THIRD_SEM,
    val firstSemCgpa: String = "",
    val secondSemCgpa: String = "",
    val thirdSemCgpa: String = "",
    val fourthSemCgpa: String = "",
    val fifthSemCgpa: String = "",
    val sixthSemCgpa: String = "",
    val seventhSemCgpa: String = "",
    val totalGpa: Double = 0.0,
    val percentage:Double = 0.0,
)
