package com.rkbapps.makautsgpaygpacalculator.utils


fun calculatePercentage(sgpa: Double): Double {
    val percentage = (sgpa - 0.75) * 10
    return String.format("%.2f", percentage).toDouble()
}

fun calculateTotalNumber(totalSubject: Int) = totalSubject * 100

fun calculateObtainedNumber(totalSubject: Int, percentage: Double): Double {
    val totalNumber = totalSubject * 100
    val obtainedNumber = (percentage * totalNumber) / 100
    return String.format("%.2f", obtainedNumber).toDouble()
}

