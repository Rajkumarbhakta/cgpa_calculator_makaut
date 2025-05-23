package com.rkbapps.makautsgpaygpacalculator.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rkbapps.makautsgpaygpacalculator.db.entity.GpaPercentage
import kotlinx.coroutines.flow.Flow


@Dao
interface GpaPercentageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gpaPercentage: GpaPercentage)

    @Delete
    suspend fun delete(gpaPercentage: GpaPercentage)

    @Update
    suspend fun update(gpaPercentage: GpaPercentage)

    @Query("SELECT * FROM gpapercentage")
    fun getAll(): Flow<List<GpaPercentage>>

    @Query("select * from gpapercentage order by timeStamps desc")
    fun getAllSortedByTimeDesc(): Flow<List<GpaPercentage>>

    @Query("select * from gpapercentage order by timeStamps asc")
    fun getAllSortedByTimeAsc(): Flow<List<GpaPercentage>>

    @Query("select * from gpapercentage where isFavorite=:isFavorite")
    fun getAllFavorite(isFavorite: Boolean = true): Flow<List<GpaPercentage>>


    @Query("select * from gpapercentage where isFavorite=:isFavorite order by timeStamps desc")
    fun getAllFavoriteSortedByTimeDesc(isFavorite: Boolean = true): Flow<List<GpaPercentage>>


    @Query("select * from gpapercentage where isFavorite=:isFavorite order by timeStamps asc")
    fun getAllFavoriteSortedByTimeAsc(isFavorite: Boolean = true): Flow<List<GpaPercentage>>

    @Query("delete from gpapercentage where isFavorite=:isFavorite")
    suspend fun deleteAll(isFavorite: Boolean = false)


}