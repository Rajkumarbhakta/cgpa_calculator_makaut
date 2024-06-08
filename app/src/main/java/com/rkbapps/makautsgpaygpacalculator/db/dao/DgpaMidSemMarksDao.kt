package com.rkbapps.makautsgpaygpacalculator.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarks
import kotlinx.coroutines.flow.Flow

@Dao
interface DgpaMidSemMarksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dgpaMidSemMarks: DgpaMidSemMarks)

    @Update
    suspend fun update(dgpaMidSemMarks: DgpaMidSemMarks)

    @Delete
    suspend fun delete(dgpaMidSemMarks: DgpaMidSemMarks)

    @Query("SELECT * FROM DgpaMidSemMarks")
     fun getAll(): Flow<List<DgpaMidSemMarks>>

    @Query("select * from dgpamidsemmarks where isFavorite=:isFavorite")
     fun getFavorite(isFavorite: Boolean = true): Flow<List<DgpaMidSemMarks>>

     @Query("select * from dgpamidsemmarks where type not in (:excludeType) ")
     fun getExcludeType(excludeType: List<String>): Flow<List<DgpaMidSemMarks>>

    @Query("SELECT * FROM DgpaMidSemMarks WHERE type NOT IN (:excludedTypes) ORDER BY timestamp DESC")
    fun getMidSemMarksExceptExcludedOrderedByTimestampDesc(excludedTypes: List<String>): Flow<List<DgpaMidSemMarks>>

    @Query("SELECT * FROM DgpaMidSemMarks WHERE type NOT IN (:excludedTypes) ORDER BY timestamp ASC")
    fun getMidSemMarksExceptExcludedOrderedByTimestampAsc(excludedTypes: List<String>): Flow<List<DgpaMidSemMarks>>

    @Query("SELECT * FROM DgpaMidSemMarks WHERE type IN (:includedTypes) ORDER BY timestamp DESC")
    fun getMidSemMarksOrderedByTimestampDesc(includedTypes: List<String>): Flow<List<DgpaMidSemMarks>>

    @Query("SELECT * FROM DgpaMidSemMarks WHERE type IN (:includedTypes) ORDER BY timestamp ASC")
    fun getMidSemMarksOrderedByTimestampAsc(includedTypes: List<String>): Flow<List<DgpaMidSemMarks>>


    @Query("SELECT * FROM DgpaMidSemMarks WHERE isFavorite = 1 AND type NOT IN (:excludedTypes) ORDER BY timestamp ASC")
    fun getFavoriteMidSemMarksExceptTypesAsc(excludedTypes: List<String>): Flow<List<DgpaMidSemMarks>>

    @Query("SELECT * FROM DgpaMidSemMarks WHERE isFavorite = 1 AND type NOT IN (:excludedTypes) ORDER BY timestamp DESC")
    fun getFavoriteMidSemMarksExceptTypesDesc(excludedTypes: List<String>): Flow<List<DgpaMidSemMarks>>

    @Query("DELETE FROM DgpaMidSemMarks WHERE type IN (:excludedTypes)")
    suspend fun deleteMidSemMarksByTypesTransaction(excludedTypes: List<String>)

    @Transaction
    suspend fun deleteMidSemMarks(vararg excludedTypes: String) {
        deleteMidSemMarksByTypesTransaction(excludedTypes.toList())
    }



}