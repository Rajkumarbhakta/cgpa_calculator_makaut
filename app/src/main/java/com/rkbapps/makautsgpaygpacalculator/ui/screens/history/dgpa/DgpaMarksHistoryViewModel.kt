package com.rkbapps.makautsgpaygpacalculator.ui.screens.history.dgpa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DgpaMarksHistoryViewModel @Inject constructor(private val repository: DgpaMarksHistoryRepository):ViewModel() {

    val dgpaHistory = repository.dgpaMarksHistory

    fun getHistoryInDesc()=viewModelScope.launch(Dispatchers.IO) {
        repository.getAllDgpaHistoryDesc()
    }

    fun getHistoryInAsc()=viewModelScope.launch(Dispatchers.IO) {
        repository.getAllDgpaHistoryAsc()
    }

    fun deleteHistory()=viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllHistory()
    }

    fun delete(item: DgpaMidSemMarks) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(item)
    }
}