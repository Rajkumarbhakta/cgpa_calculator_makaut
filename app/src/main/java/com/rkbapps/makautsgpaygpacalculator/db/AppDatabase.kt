package com.rkbapps.makautsgpaygpacalculator.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rkbapps.makautsgpaygpacalculator.db.dao.DgpaMidSemMarksDao
import com.rkbapps.makautsgpaygpacalculator.db.dao.GpaPercentageDao
import com.rkbapps.makautsgpaygpacalculator.db.dao.YearlyMarksDao
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarks
import com.rkbapps.makautsgpaygpacalculator.db.entity.GpaPercentage
import com.rkbapps.makautsgpaygpacalculator.db.entity.YearlyMarks

/**
 * The Room database for this app.
 *
 * This class defines the database schema and provides access to the DAOs.
 * It is annotated with `@Database` to indicate that it is a Room database.
 * The `entities` parameter specifies the list of entity classes that will be stored in the database.
 * The `version` parameter specifies the database version. If you change the database schema,
 * you need to increment the version number and provide a migration strategy.
 */
@Database(
    entities = [DgpaMidSemMarks::class, GpaPercentage::class, YearlyMarks::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dgpaMidSemMarksDao(): DgpaMidSemMarksDao
    abstract fun gpaPercentageDao(): GpaPercentageDao
    abstract fun yearlyMarksDao(): YearlyMarksDao
}