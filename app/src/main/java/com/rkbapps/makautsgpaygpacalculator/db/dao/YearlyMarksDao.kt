package com.rkbapps.makautsgpaygpacalculator.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rkbapps.makautsgpaygpacalculator.db.entity.YearlyMarks
import kotlinx.coroutines.flow.Flow


@Dao
interface YearlyMarksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(yearlyMarks: YearlyMarks)

    @Update
    suspend fun update(yearlyMarks: YearlyMarks)

    @Delete
    suspend fun delete(yearlyMarks: YearlyMarks)


    @Query("SELECT * FROM yearlymarks")
    fun getAll(): Flow<List<YearlyMarks>>

    @Query("select * from yearlymarks where isFavourite=:isFavourite")
    fun getFavourite(isFavourite: Boolean = true): Flow<List<YearlyMarks>>

    @Query("select * from yearlymarks order by timeStamp desc")
    fun getAllSortedByTimeStamp(): Flow<List<YearlyMarks>>

    @Query("select * from yearlymarks order by timeStamp asc")
    fun getAllSortedByTimeStampAsc(): Flow<List<YearlyMarks>>

    @Query("select * from yearlymarks where isFavourite=:isFavourite order by timeStamp desc")
    fun getFavouriteSortedByTimeStamp(isFavourite: Boolean = true): Flow<List<YearlyMarks>>

    @Query("select * from yearlymarks where isFavourite=:isFavourite order by timeStamp asc")
    fun getFavouriteSortedByTimeStampAsc(isFavourite: Boolean = true): Flow<List<YearlyMarks>>

    @Query("delete from yearlymarks where isFavourite=:isFavourite")
    suspend fun deleteHistory(isFavourite: Boolean = false)


}