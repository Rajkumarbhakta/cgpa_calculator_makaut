package com.rkbapps.makautsgpaygpacalculator.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rkbapps.makautsgpaygpacalculator.db.dao.DgpaMidSemMarksDao
import com.rkbapps.makautsgpaygpacalculator.db.dao.GpaPercentageDao
import com.rkbapps.makautsgpaygpacalculator.db.dao.YearlyMarksDao
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarks
import com.rkbapps.makautsgpaygpacalculator.db.entity.GpaPercentage
import com.rkbapps.makautsgpaygpacalculator.db.entity.YearlyMarks

@Database(entities = [DgpaMidSemMarks::class,GpaPercentage::class,YearlyMarks::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dgpaMidSemMarksDao(): DgpaMidSemMarksDao
    abstract fun gpaPercentageDao(): GpaPercentageDao
    abstract fun yearlyMarksDao(): YearlyMarksDao
}