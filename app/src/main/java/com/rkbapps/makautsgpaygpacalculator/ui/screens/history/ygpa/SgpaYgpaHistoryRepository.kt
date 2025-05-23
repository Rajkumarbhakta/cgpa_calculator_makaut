package com.rkbapps.makautsgpaygpacalculator.ui.screens.history.ygpa

import com.rkbapps.makautsgpaygpacalculator.db.dao.GpaPercentageDao
import com.rkbapps.makautsgpaygpacalculator.db.entity.GpaPercentage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject

class SgpaYgpaHistoryRepository @Inject constructor(private val gpaPercentageDao: GpaPercentageDao) {
    private val _sgpaYgpaHistory = MutableStateFlow<List<GpaPercentage>>(emptyList())
    val sgpaYgpaHistory: StateFlow<List<GpaPercentage>> = _sgpaYgpaHistory


    suspend fun getAllSortedByTimeDesc() {
        try {
            val marks = gpaPercentageDao.getAllSortedByTimeAsc()
            _sgpaYgpaHistory.emitAll(marks)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getAllSortedByTimeAsc() {
        try {
            val marks = gpaPercentageDao.getAllSortedByTimeAsc()
            _sgpaYgpaHistory.emitAll(marks)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun delete(gpaPercentage: GpaPercentage) {
        try {
            gpaPercentageDao.delete(gpaPercentage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun deleteAll() {
        try {
            gpaPercentageDao.deleteAll(isFavorite = false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}