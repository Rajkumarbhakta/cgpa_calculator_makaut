package com.rkbapps.makautsgpaygpacalculator.ui.screens.yearly

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.makautsgpaygpacalculator.db.dao.YearlyMarksDao
import com.rkbapps.makautsgpaygpacalculator.db.entity.YearlyMarks
import com.rkbapps.makautsgpaygpacalculator.utils.calculateObtainedNumber
import com.rkbapps.makautsgpaygpacalculator.utils.calculatePercentage
import com.rkbapps.makautsgpaygpacalculator.utils.calculateTotalNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YearlyMarksViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val yearlyMarksDao: YearlyMarksDao
) : ViewModel() {
    private val _state = MutableStateFlow(YearlyMarksConverterState())
    val state =_state.asStateFlow()



    fun insert(yearlyMarks: YearlyMarks) = viewModelScope.launch(Dispatchers.IO) {
        yearlyMarksDao.insert(yearlyMarks)
    }

    fun calculate(){
        try {
            if (state.value.oddSemTotalSubject.isNotEmpty() && state.value.oddSemSgpa.isNotEmpty()) {
                if (state.value.oddSemSgpa.toDouble() <= 10) {
                    try {
                        val oddSemTotalNumber = calculateTotalNumber(state.value.oddSemTotalSubject.toInt()).toString()
                        val oddSemPercentage = calculatePercentage(state.value.oddSemSgpa.toDouble()).toString()

                        val oddSemObtainedNumber= calculateObtainedNumber(
                            state.value.oddSemTotalSubject.toInt(),
                            oddSemPercentage.toDouble()
                        ).toString()

                        _state.update {
                            it.copy(
                                oddSemTotalNumber = oddSemTotalNumber,
                                oddSemPercentage = oddSemPercentage,
                                oddSemObtainedNumber = oddSemObtainedNumber
                            )
                        }
                    } catch (_: Exception) {
                        Toast.makeText(
                            context, "Please provide proper odd sem details.", Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Odd semester sgpa should be between 1 to 10.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            if (state.value.evenSemTotalSubject.isNotEmpty() && state.value.evenSemSgpa.isNotEmpty()) {
                if (state.value.evenSemSgpa.toDouble() <= 10) {
                    try {
                        val evenSemTotalNumber = calculateTotalNumber(state.value.evenSemTotalSubject.toInt()).toString()
                        val evenSemPercentage =
                            calculatePercentage(state.value.evenSemSgpa.toDouble()).toString()
                        val evenSemObtainedNumber = calculateObtainedNumber(
                            state.value.evenSemTotalSubject.toInt(),
                            evenSemPercentage.toDouble()
                        ).toString()

                        _state.update {
                            it.copy(
                                evenSemTotalNumber = evenSemTotalNumber,
                                evenSemPercentage = evenSemPercentage,
                                evenSemObtainedNumber = evenSemObtainedNumber
                            )
                        }
                    } catch (_: Exception) {
                        Toast.makeText(
                            context,
                            "Please provide proper even sem details.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Even semester sgpa should be between 1 to 10.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


            if (state.value.oddSemObtainedNumber.isNotEmpty() && state.value.evenSemObtainedNumber.isNotEmpty()){
                val totalSubjects = state.value.oddSemTotalSubject.toInt() + state.value.evenSemTotalSubject.toInt()
                val yearTotalNumber = calculateTotalNumber(totalSubjects).toString()
                val yearPercentage = calculatePercentage((state.value.oddSemSgpa.toDouble() + state.value.evenSemSgpa.toDouble()) / 2.0).toString()
                val yearObtainedNumber = calculateObtainedNumber(
                    totalSubjects,
                    yearPercentage.toDouble()
                ).toString()

                _state.update {
                    it.copy(
                        yearPercentage = yearPercentage,
                        yearTotalNumber = yearTotalNumber,
                        yearObtainedNumber = yearObtainedNumber
                    )
                }

            }

            //saving history of calculation
            if (state.value.oddSemObtainedNumber.isNotEmpty() || state.value.evenSemObtainedNumber.isNotEmpty()) {
                insert(
                    YearlyMarks(
                        //even sem
                        evenSemSubjects = if (state.value.evenSemTotalSubject.isNotEmpty() && state.value.evenSemSgpa.isNotEmpty()) state.value.evenSemTotalSubject.toInt() else 0,
                        evenSemGpa = if (state.value.evenSemTotalSubject.isNotEmpty() && state.value.evenSemSgpa.isNotEmpty()) state.value.evenSemSgpa.toDouble() else 0.0,
                        evenSemPercentage = if (state.value.evenSemPercentage.isNotEmpty() && state.value.evenSemSgpa.isNotEmpty()) state.value.evenSemPercentage.toDouble() else 0.0,
                        evenSemObtainedMarks = if (state.value.evenSemObtainedNumber.isNotEmpty() && state.value.evenSemSgpa.isNotEmpty()) state.value.evenSemObtainedNumber.toDouble() else 0.0,
                        evenSemTotalMarks = if (state.value.evenSemTotalNumber.isNotEmpty() && state.value.evenSemSgpa.isNotEmpty()) state.value.evenSemTotalNumber.toInt() else 0,
                        //odd sem
                        oddSemSubjects = if (state.value.oddSemTotalSubject.isNotEmpty() && state.value.oddSemSgpa.isNotEmpty()) state.value.oddSemTotalSubject.toInt() else 0,
                        oddSemGpa = if (state.value.oddSemTotalSubject.isNotEmpty() && state.value.oddSemSgpa.isNotEmpty()) state.value.oddSemSgpa.toDouble() else 0.0,
                        oddSemPercentage = if (state.value.oddSemPercentage.isNotEmpty() && state.value.oddSemSgpa.isNotEmpty()) state.value.oddSemPercentage.toDouble() else 0.0,
                        oddSemObtainedMarks = if (state.value.oddSemObtainedNumber.isNotEmpty() && state.value.oddSemSgpa.isNotEmpty()) state.value.oddSemObtainedNumber.toDouble() else 0.0,
                        oddSemTotalMarks = if (state.value.oddSemTotalNumber.isNotEmpty() && state.value.oddSemSgpa.isNotEmpty()) state.value.oddSemTotalNumber.toInt() else 0,
                        //total calculation
                        totalMarks = if (state.value.yearTotalNumber.isNotEmpty() && state.value.yearPercentage.isNotEmpty()) state.value.yearTotalNumber.toInt() else 0,
                        totalPercentage = if (state.value.yearPercentage.isNotEmpty() && state.value.yearTotalNumber.isNotEmpty()) state.value.yearPercentage.toDouble() else 0.0,
                        totalObtainedMarks = if (state.value.yearObtainedNumber.isNotEmpty() && state.value.yearPercentage.isNotEmpty()) state.value.yearObtainedNumber.toDouble() else 0.0,
                    )
                )
            }


        } catch (_: Exception) {
            Toast.makeText(
                context, "Something went wrong.Please provide details properly.", Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun update(state: YearlyMarksConverterState){
        _state.update {
            state
        }
    }

    fun clear(){
        _state.update {
            YearlyMarksConverterState()
        }
    }



}