package com.rkbapps.makautsgpaygpacalculator.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class YearlyMarks(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val oddSemGpa: Double = 0.0,
    val evenSemGpa: Double=0.0,

    val oddSemSubjects: Int=0,
    val evenSemSubjects: Int=0,

    val evenSemPercentage: Double=0.0,
    val oddSemPercentage: Double=0.0,

    val oddSemObtainedMarks: Double=0.0,
    val evenSemObtainedMarks: Double=0.0,

    val oddSemTotalMarks: Int=0,
    val evenSemTotalMarks:Int=0,

    val totalObtainedMarks: Double=0.0,
    val totalMarks: Int=0,
    val totalPercentage: Double=0.0,

    val isFavourite: Boolean=false,

    val timeStamp: Long = System.currentTimeMillis()
)