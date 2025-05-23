package com.rkbapps.makautsgpaygpacalculator.ui.screens.midsem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.makautsgpaygpacalculator.db.dao.DgpaMidSemMarksDao
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MidSemCalculatorViewModel @Inject constructor(private val dgpaMidSemMarksDao: DgpaMidSemMarksDao) :
    ViewModel() {

    fun insert(dgpaMidSemMarks: DgpaMidSemMarks) = viewModelScope.launch(Dispatchers.IO) {
        try {
            dgpaMidSemMarksDao.insert(dgpaMidSemMarks)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}