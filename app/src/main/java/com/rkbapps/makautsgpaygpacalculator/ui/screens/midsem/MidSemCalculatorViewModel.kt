package com.rkbapps.makautsgpaygpacalculator.ui.screens.midsem

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rkbapps.makautsgpaygpacalculator.db.dao.DgpaMidSemMarksDao
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarks
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarksTypes
import com.rkbapps.makautsgpaygpacalculator.utils.calculatePercentage
import com.rkbapps.makautsgpaygpacalculator.utils.formateDouble
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MidSemCalculatorViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dgpaMidSemMarksDao: DgpaMidSemMarksDao
) : ViewModel() {
    private val _state = MutableStateFlow(MidSemCalculatorState())
    val state = _state.map {state ->
        when(state.selectedOption){
            Options.THIRD_SEM-> {
                state.copy(
                    fourthSemCgpa = "",
                    fifthSemCgpa = "",
                    sixthSemCgpa = "",
                    seventhSemCgpa = "",
                )
            }
            Options.FOURTH_SEM -> {
                state.copy(
                    fifthSemCgpa = "",
                    sixthSemCgpa = "",
                    seventhSemCgpa = "",
                )
            }
            Options.FIFTH_SEM -> {
                state.copy(
                    sixthSemCgpa = "",
                    seventhSemCgpa = "",
                )
            }
            Options.SIXTH_SEM -> {
                state.copy(
                    seventhSemCgpa = "",
                )
            }
            Options.SEVENTH_SEM -> {
                state
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        MidSemCalculatorState()
    )


    fun insert(dgpaMidSemMarks: DgpaMidSemMarks) = viewModelScope.launch(Dispatchers.IO) {
        try {
            dgpaMidSemMarksDao.insert(dgpaMidSemMarks)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun update(value: MidSemCalculatorState){
        _state.update {
            value
        }
    }


    fun calculate(){
        try {
            val first = if (state.value.firstSemCgpa.isEmpty()) 0.0 else state.value.firstSemCgpa.toDouble()
            val second = if (state.value.secondSemCgpa.isEmpty()) 0.0 else state.value.secondSemCgpa.toDouble()
            val third = if (state.value.thirdSemCgpa.isEmpty()) 0.0 else state.value.thirdSemCgpa.toDouble()
            val fourth = if (state.value.fourthSemCgpa.isEmpty()) 0.0 else state.value.fourthSemCgpa.toDouble()
            val fifth = if (state.value.fifthSemCgpa.isEmpty()) 0.0 else state.value.fifthSemCgpa.toDouble()
            val sixth = if (state.value.sixthSemCgpa.isEmpty()) 0.0 else state.value.sixthSemCgpa.toDouble()
            val seventh = if (state.value.seventhSemCgpa.isEmpty()) 0.0 else state.value.seventhSemCgpa.toDouble()
            val total = first + second + third + fourth + fifth + sixth + seventh
            val count = state.value.selectedOption.count

            val totalCgpa = formateDouble(total / count.toDouble())

            val percentage = calculatePercentage(total / count.toDouble())
            insert(
                DgpaMidSemMarks(
                    type = when (state.value.selectedOption) {
                        Options.THIRD_SEM -> {
                            DgpaMidSemMarksTypes.MID_SEM_3_SEM
                        }
                        Options.FOURTH_SEM -> {
                            DgpaMidSemMarksTypes.MID_SEM_4_SEM
                        }
                        Options.FIFTH_SEM -> {
                            DgpaMidSemMarksTypes.MID_SEM_5_SEM
                        }
                        Options.SIXTH_SEM -> {
                            DgpaMidSemMarksTypes.MID_SEM_6_SEM
                        }
                        Options.SEVENTH_SEM -> {
                            DgpaMidSemMarksTypes.MID_SEM_7_SEM
                        }
                    },
                    firstSemGpa = first,
                    secondSemGpa = second,
                    thirdSemGpa = third,
                    fourthSemGpa = fourth,
                    fifthSemGpa = fifth,
                    sixthSemGpa = sixth,
                    seventhSemGpa = seventh,
                    avgGpa = totalCgpa?:0.0,
                    avgPercentage = percentage
                )
            )

        } catch (_: Exception) {
            Toast.makeText(context, "Please enter CGPA properly.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun clear(){
        _state.update {
            it.copy(
                firstSemCgpa = "",
                secondSemCgpa = "",
                thirdSemCgpa = "",
                fourthSemCgpa = "",
                fifthSemCgpa = "",
                sixthSemCgpa = "",
                seventhSemCgpa = "",
                totalGpa = 0.0,
                percentage = 0.0
            )
        }
    }

}