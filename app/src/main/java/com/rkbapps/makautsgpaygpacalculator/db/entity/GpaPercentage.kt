package com.rkbapps.makautsgpaygpacalculator.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class GpaPercentage (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val gpa: Double,
    val percentage: Double,

    val isFavorite: Boolean = false,

    val timeStamps: Long = System.currentTimeMillis()


)