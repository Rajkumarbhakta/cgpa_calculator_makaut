package com.rkbapps.makautsgpaygpacalculator.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DgpaMidSemMarks (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val type: String,

    val firstSemGpa: Double = 0.0,
    val secondSemGpa: Double = 0.0,
    val thirdSemGpa: Double = 0.0,
    val fourthSemGpa: Double = 0.0,
    val fifthSemGpa: Double = 0.0,
    val sixthSemGpa: Double = 0.0,
    val seventhSemGpa: Double = 0.0,
    val eighthSemGpa: Double = 0.0,

    val avgGpa: Double = 0.0,

    val avgPercentage: Double = 0.0,

    val timestamp: Long = System.currentTimeMillis(),

    val isFavorite: Boolean = false
)

object DgpaMidSemMarksTypes{
    const val MID_SEM_3_SEM = "MID_SEM_3_SEM"
    const val MID_SEM_4_SEM = "MID_SEM_4_SEM"
    const val MID_SEM_5_SEM = "MID_SEM_5_SEM"
    const val MID_SEM_6_SEM = "MID_SEM_6_SEM"
    const val MID_SEM_7_SEM = "MID_SEM_7_SEM"

    const val DGPA_4_YEAR = "DGPA_4_YEAR"
    const val DGPA_3_YEAR = "DGPA_3_YEAR"

}