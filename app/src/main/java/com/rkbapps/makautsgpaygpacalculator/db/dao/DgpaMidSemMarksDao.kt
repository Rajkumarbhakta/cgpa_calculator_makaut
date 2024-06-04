package com.rkbapps.makautsgpaygpacalculator.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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


}