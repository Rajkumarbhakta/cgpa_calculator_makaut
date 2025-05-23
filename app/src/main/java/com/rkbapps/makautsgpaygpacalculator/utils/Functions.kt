package com.rkbapps.makautsgpaygpacalculator.utils

import java.util.Locale


/**
 * Calculates the percentage from the SGPA.
 *
 * @param sgpa The SGPA (Semester Grade Point Average) to calculate the percentage from.
 * @return The calculated percentage, formatted to two decimal places.
 */
fun calculatePercentage(sgpa: Double): Double {
    val percentage = (sgpa - 0.75) * 10
    return String.format(locale = Locale.getDefault(), "%.2f", percentage).toDouble()
}

/**
 * Calculates the total number of marks based on the number of subjects.
 * Each subject is assumed to have a maximum of 100 marks.
 *
 * @param totalSubject The total number of subjects.
 * @return The total possible marks.
 */
fun calculateTotalNumber(totalSubject: Int) = totalSubject * 100

/**
 * Calculates the obtained number based on the total number of subjects and the percentage.
 *
 * @param totalSubject The total number of subjects.
 * @param percentage The percentage obtained.
 * @return The calculated obtained number, formatted to two decimal places.
 */
fun calculateObtainedNumber(totalSubject: Int, percentage: Double): Double {
    val totalNumber = totalSubject * 100
    val obtainedNumber = (percentage * totalNumber) / 100
    return String.format(locale = Locale.getDefault(), "%.2f", obtainedNumber).toDouble()
}

