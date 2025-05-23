package com.rkbapps.makautsgpaygpacalculator.ui.screens.ygpa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.makautsgpaygpacalculator.db.entity.GpaPercentage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SgpaYgpaPercentageViewModel @Inject constructor(private val repository: SgpaYgpaPercentageRepository) :
    ViewModel() {

    fun insert(gpaPercentage: GpaPercentage) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(gpaPercentage)
    }


}