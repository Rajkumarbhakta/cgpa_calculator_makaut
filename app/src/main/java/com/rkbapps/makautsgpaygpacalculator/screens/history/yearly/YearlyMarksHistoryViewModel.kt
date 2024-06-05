package com.rkbapps.makautsgpaygpacalculator.screens.history.yearly

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.makautsgpaygpacalculator.db.entity.YearlyMarks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YearlyMarksHistoryViewModel @Inject constructor(private val repository: YearlyMarksHistoryRepository) : ViewModel() {

    val yearlyCalculatedMarks: StateFlow<List<YearlyMarks>> = repository.yearlyCalculatedMarks

    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getAllSortedDesc()
//        }
    }

    fun deleteYearlyMarks(yearlyMarks: YearlyMarks) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAEntity(yearlyMarks)
        }
    }

    fun getAllSortedDesc() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllSortedDesc()
        }
    }

    fun getAllSortedAsc() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllSortedAsc()
        }
    }

    fun clearHistory(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearHistory()
        }
    }


}