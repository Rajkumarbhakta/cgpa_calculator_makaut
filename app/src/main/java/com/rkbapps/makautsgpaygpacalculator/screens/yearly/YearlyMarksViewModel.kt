package com.rkbapps.makautsgpaygpacalculator.screens.yearly

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.makautsgpaygpacalculator.db.dao.YearlyMarksDao
import com.rkbapps.makautsgpaygpacalculator.db.entity.YearlyMarks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YearlyMarksViewModel @Inject constructor(private val yearlyMarksDao: YearlyMarksDao):ViewModel() {


     fun insert(yearlyMarks: YearlyMarks) = viewModelScope.launch {
        yearlyMarksDao.insert(yearlyMarks)
    }



}