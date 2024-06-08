package com.rkbapps.makautsgpaygpacalculator.screens.history.midsem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MidSemCalculationHistoryViewModel @Inject constructor(private val repository: MidSemCalculationHistoryRepository): ViewModel() {
    val midSemHistory = repository.midSemHistory

    fun getHistoryInDesc()=viewModelScope.launch(Dispatchers.IO) {
        repository.getAllMidSemHistoryDesc()
    }

    fun getHistoryInAsc()=viewModelScope.launch(Dispatchers.IO) {
        repository.getAllMidSemHistoryAsc()
    }

    fun deleteHistory()=viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllHistory()
    }

    fun delete(item:DgpaMidSemMarks) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(item)
    }
}