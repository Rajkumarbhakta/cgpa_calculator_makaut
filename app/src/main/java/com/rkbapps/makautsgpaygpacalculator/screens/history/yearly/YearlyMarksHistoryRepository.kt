package com.rkbapps.makautsgpaygpacalculator.screens.history.yearly

import com.rkbapps.makautsgpaygpacalculator.db.dao.YearlyMarksDao
import com.rkbapps.makautsgpaygpacalculator.db.entity.YearlyMarks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject


class YearlyMarksHistoryRepository @Inject constructor(private val yearlyMarksDao: YearlyMarksDao) {

    private val _yearlyCalculatedMarks = MutableStateFlow<List<YearlyMarks>>(emptyList())
    val yearlyCalculatedMarks:StateFlow<List<YearlyMarks>> = _yearlyCalculatedMarks

    suspend fun getAllYearlyMarks() {
        try {
            val marks = yearlyMarksDao.getAll()
            _yearlyCalculatedMarks.emitAll(marks)
        }catch (e:Exception){
         e.printStackTrace()
        }

    }

    suspend fun getAllSortedDesc() {
        try {
            val marks = yearlyMarksDao.getAllSortedByTimeStamp()
            _yearlyCalculatedMarks.emitAll(marks)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getAllSortedAsc() {
        try {
            val marks = yearlyMarksDao.getAllSortedByTimeStampAsc()
            _yearlyCalculatedMarks.emitAll(marks)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }




    suspend fun getAllFavouriteMarks(){
        try {
            val marks = yearlyMarksDao.getFavourite(isFavourite = true)
            _yearlyCalculatedMarks.emitAll(marks)
        }catch (e:Exception) {
            e.printStackTrace()
        }
    }

    suspend fun markAsFavourite(yearlyMarks: YearlyMarks) {
        try {
            yearlyMarks.isFavourite = true
            yearlyMarksDao.update(yearlyMarks)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun  deleteFavourite(yearlyMarks: YearlyMarks){
        try {
            yearlyMarks.isFavourite = false
            yearlyMarksDao.update(yearlyMarks)
        }catch (e:Exception) {
            e.printStackTrace()
        }
    }

    suspend fun deleteAEntity(yearlyMarks: YearlyMarks){
        try {
            yearlyMarksDao.delete(yearlyMarks)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    suspend fun clearHistory() {
        try {
            yearlyMarksDao.deleteHistory(isFavourite = false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}
