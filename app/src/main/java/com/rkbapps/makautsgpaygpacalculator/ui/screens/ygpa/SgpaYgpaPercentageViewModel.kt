package com.rkbapps.makautsgpaygpacalculator.ui.screens.ygpa

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.makautsgpaygpacalculator.db.entity.GpaPercentage
import com.rkbapps.makautsgpaygpacalculator.utils.calculatePercentage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SgpaYgpaPercentageViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: SgpaYgpaPercentageRepository
) :
    ViewModel() {

        private val _state = MutableStateFlow(SgpaYgpaPercentageState())
    val state = _state.asStateFlow()


    fun insert(gpaPercentage: GpaPercentage) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(gpaPercentage)
    }


    fun updateCgpa(cgpa:String){
        _state.update {
            it.copy(cgpaYgpa = cgpa)
        }
    }

    fun updatePercentage(percentage: Double){
        _state.update {
            it.copy(percentage = percentage)
        }
    }

    fun calculate(){
        try {
            if (state.value.cgpaYgpa.isNotEmpty() && state.value.cgpaYgpa.toDouble() <= 10) {
                val cgpa = state.value.cgpaYgpa.toDouble()
                val percentage = calculatePercentage(cgpa)
                updatePercentage(percentage)
                insert(GpaPercentage(gpa = cgpa, percentage = percentage))
            } else {
                Toast.makeText(context, "Enter CGPA/YGPA.", Toast.LENGTH_SHORT)
                    .show()
            }
        } catch (_: Exception) {
            Toast.makeText(context, "Enter proper CGPA/YGPA.", Toast.LENGTH_SHORT)
                .show()
        }
    }


    fun clear(){
        _state.update {
            SgpaYgpaPercentageState()
        }
    }
}