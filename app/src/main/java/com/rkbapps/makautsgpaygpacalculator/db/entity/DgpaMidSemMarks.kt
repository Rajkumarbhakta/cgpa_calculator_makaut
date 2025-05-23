package com.rkbapps.makautsgpaygpacalculator.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents the DGPA/Mid-Semester marks for a student.
 * This class is an Entity for the Room database.
 *
 * @property id The unique identifier for the record (auto-generated).
 * @property type The type of calculation (e.g., "MID_SEM_3_SEM", "DGPA_4_YEAR").
 * @property firstSemGpa GPA for the first semester.
 * @property secondSemGpa GPA for the second semester.
 * @property thirdSemGpa GPA for the third semester.
 * @property fourthSemGpa GPA for the fourth semester.
 * @property fifthSemGpa GPA for the fifth semester.
 * @property sixthSemGpa GPA for the sixth semester.
 * @property seventhSemGpa GPA for the seventh semester.
 * @property eighthSemGpa GPA for the eighth semester.
 * @property avgGpa The average GPA calculated.
 * @property avgPercentage The average percentage calculated.
 * @property timestamp The timestamp when the record was created or last updated.
 * @property isFavorite Indicates if this record is marked as a favorite.
 */
@Entity
data class DgpaMidSemMarks(
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

/**
 * An object that defines the types of DGPA and Mid-Semester marks.
 *
 * This object contains constants that represent the different types of DGPA and Mid-Semester marks
 * that can be stored in the database. These constants are used to differentiate between different
 * types of calculations, such as DGPA for a 4-year course or Mid-Semester marks for the 3rd semester.
 */
object DgpaMidSemMarksTypes {
    const val MID_SEM_3_SEM = "MID_SEM_3_SEM"
    const val MID_SEM_4_SEM = "MID_SEM_4_SEM"
    const val MID_SEM_5_SEM = "MID_SEM_5_SEM"
    const val MID_SEM_6_SEM = "MID_SEM_6_SEM"
    const val MID_SEM_7_SEM = "MID_SEM_7_SEM"

    const val DGPA_4_YEAR = "DGPA_4_YEAR"
    const val DGPA_3_YEAR = "DGPA_3_YEAR"

}