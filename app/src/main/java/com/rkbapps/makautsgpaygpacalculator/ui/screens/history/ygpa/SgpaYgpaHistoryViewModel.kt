package com.rkbapps.makautsgpaygpacalculator.ui.screens.history.ygpa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.makautsgpaygpacalculator.db.entity.GpaPercentage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SgpaYgpaHistoryViewModel @Inject constructor(private val repository: com.rkbapps.makautsgpaygpacalculator.ui.screens.history.ygpa.SgpaYgpaHistoryRepository):ViewModel() {
    val sgpaYgpaHistory: StateFlow<List<GpaPercentage>> = repository.sgpaYgpaHistory


    fun getAllByTimeDesc(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllSortedByTimeDesc()
        }
    }

    fun getAllByTimeAsc(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllSortedByTimeAsc()
        }
    }

    fun delete(gpaPercentage: GpaPercentage){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(gpaPercentage)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }



}