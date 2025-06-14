package com.rkbapps.makautsgpaygpacalculator.ui.screens.history.dgpa

import com.rkbapps.makautsgpaygpacalculator.db.dao.DgpaMidSemMarksDao
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarks
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarksTypes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject

class DgpaMarksHistoryRepository @Inject constructor(private val dao: DgpaMidSemMarksDao) {


    private val includedType = listOf(
        DgpaMidSemMarksTypes.DGPA_3_YEAR,
        DgpaMidSemMarksTypes.DGPA_4_YEAR,
    )

    private val excludedType = listOf(
        DgpaMidSemMarksTypes.MID_SEM_3_SEM,
        DgpaMidSemMarksTypes.MID_SEM_4_SEM,
        DgpaMidSemMarksTypes.MID_SEM_5_SEM,
        DgpaMidSemMarksTypes.MID_SEM_6_SEM,
        DgpaMidSemMarksTypes.MID_SEM_7_SEM,
    )


    private val _dgpaMarksHistory = MutableStateFlow<List<DgpaMidSemMarks>>(emptyList())
    val dgpaMarksHistory: StateFlow<List<DgpaMidSemMarks>> = _dgpaMarksHistory


    suspend fun getAllDgpaHistoryDesc() {
        try {
            val history = dao.getMidSemMarksExceptExcludedOrderedByTimestampAsc(excludedType)
            _dgpaMarksHistory.emitAll(history)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    suspend fun getAllDgpaHistoryAsc() {
        try {
            val history = dao.getMidSemMarksExceptExcludedOrderedByTimestampDesc(excludedType)
            _dgpaMarksHistory.emitAll(history)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    suspend fun deleteAllHistory() {
        try {
            dao.deleteMidSemMarks(*includedType.toTypedArray())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun delete(item: DgpaMidSemMarks) {
        try {
            dao.delete(item)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}