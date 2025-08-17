package com.rkbapps.makautsgpaygpacalculator.ui.screens.dgpa

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.makautsgpaygpacalculator.db.dao.DgpaMidSemMarksDao
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarks
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarksTypes
import com.rkbapps.makautsgpaygpacalculator.utils.calculatePercentage
import com.rkbapps.makautsgpaygpacalculator.utils.formateDouble
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DgpaCalculatorViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dao: DgpaMidSemMarksDao) :
    ViewModel() {

    private val _state = MutableStateFlow(DgpaScreenState())
    val state = _state.asStateFlow()


    private fun insert(dgpaMidSemMarks: DgpaMidSemMarks) = viewModelScope.launch(Dispatchers.IO) {
        try {
            dao.insert(dgpaMidSemMarks)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun calculateResult(){
        when(state.value.currentSelectedCourseType){
            CourseType.THREE_YEAR_DEGREE -> {
                if (state.value.firstSemMark.isNotEmpty() && state.value.secondSemMark.isNotEmpty() &&
                    state.value.thirdSemMark.isNotEmpty() && state.value.fourthSemMark.isNotEmpty() &&
                    state.value.fiveSemMark.isNotEmpty() && state.value.sixSemMark.isNotEmpty() &&
                    state.value.sevenSemMark.isNotEmpty() && state.value.eightSemMark.isNotEmpty()
                ){
                    calculateFourYearResult()
                }else{
                    Toast.makeText(context, "Enter your all eight semester SGPA.", Toast.LENGTH_SHORT).show()
                }
            }
            CourseType.THREE_YEAR_LATERAL -> {
                if (
                    state.value.thirdSemMark.isNotEmpty() && state.value.fourthSemMark.isNotEmpty() &&
                    state.value.fiveSemMark.isNotEmpty() && state.value.sixSemMark.isNotEmpty() &&
                    state.value.sevenSemMark.isNotEmpty() && state.value.eightSemMark.isNotEmpty()
                ){
                    calculateThreeYearLateralResult()
                }else{
                    Toast.makeText(context, "Enter your all six semester SGPA.", Toast.LENGTH_SHORT).show()
                }
            }
            CourseType.FOUR_YEAR_DEGREE -> {
                if (
                    state.value.firstSemMark.isNotEmpty() && state.value.secondSemMark.isNotEmpty() &&
                    state.value.thirdSemMark.isNotEmpty() && state.value.fourthSemMark.isNotEmpty() &&
                    state.value.fiveSemMark.isNotEmpty() && state.value.sixSemMark.isNotEmpty()
                ){
                    calculateThreeYearResult()
                }else{
                    Toast.makeText(context, "Enter your all six semester SGPA.", Toast.LENGTH_SHORT).show()
                }

            }
        }


    }



    private fun calculateFourYearResult(){
        try {
            val ygpa1 = (state.value.firstSemMark.toDouble() + state.value.secondSemMark.toDouble()) / 2
            val ygpa2 = (state.value.thirdSemMark.toDouble() + state.value.fourthSemMark.toDouble()) / 2
            val ygpa3 = (state.value.fiveSemMark.toDouble() + state.value.sixSemMark.toDouble()) / 2
            val ygpa4 = (state.value.sevenSemMark.toDouble() + state.value.eightSemMark.toDouble()) / 2

            val total = ygpa1 + ygpa2 + (1.5 * ygpa3) + (1.5 * ygpa4)
            val average = total / 5
            val result = formateDouble(average)
            val percentage = calculatePercentage(average)
            _state.update {
                it.copy(result = result, percentage = percentage)
            }
            insert(
                DgpaMidSemMarks(
                    type = DgpaMidSemMarksTypes.DGPA_4_YEAR,
                    firstSemGpa = state.value.firstSemMark.toDouble(),
                    secondSemGpa = state.value.secondSemMark.toDouble(),
                    thirdSemGpa = state.value.thirdSemMark.toDouble(),
                    fourthSemGpa = state.value.fourthSemMark.toDouble(),
                    fifthSemGpa = state.value.fiveSemMark.toDouble(),
                    sixthSemGpa = state.value.sixSemMark.toDouble(),
                    seventhSemGpa = state.value.sevenSemMark.toDouble(),
                    eighthSemGpa = state.value.eightSemMark.toDouble(),
                    avgGpa = result?:0.0,
                    avgPercentage = percentage
                )
            )
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun calculateThreeYearResult(){
        try {
            val ygpa1 = (state.value.firstSemMark.toDouble() + state.value.secondSemMark.toDouble()) / 2
            val ygpa2 = (state.value.thirdSemMark.toDouble() + state.value.fourthSemMark.toDouble()) / 2
            val ygpa3 = (state.value.fiveSemMark.toDouble() + state.value.sixSemMark.toDouble()) / 2

            val total = ygpa1 + ygpa2 + ygpa3

            val average = total / 3
            val result = formateDouble(average)
            val percentage = calculatePercentage(average)
            _state.update {
                it.copy(result = result, percentage = percentage)
            }
            insert(
                DgpaMidSemMarks(
                    type = DgpaMidSemMarksTypes.DGPA_3_YEAR,
                    firstSemGpa = state.value.firstSemMark.toDouble(),
                    secondSemGpa = state.value.secondSemMark.toDouble(),
                    thirdSemGpa = state.value.thirdSemMark.toDouble(),
                    fourthSemGpa = state.value.fourthSemMark.toDouble(),
                    fifthSemGpa = state.value.fiveSemMark.toDouble(),
                    sixthSemGpa = state.value.sixSemMark.toDouble(),

                    avgGpa = result?:0.0,
                    avgPercentage = percentage
                )
            )
        }catch (e: Exception){
            e.printStackTrace()
        }
    }


    private fun calculateThreeYearLateralResult(){
        try {
            val ygpa2 = (state.value.thirdSemMark.toDouble() + state.value.fourthSemMark.toDouble()) / 2
            val ygpa3 = (state.value.fiveSemMark.toDouble() + state.value.sixSemMark.toDouble()) / 2
            val ygpa4 = (state.value.sevenSemMark.toDouble() + state.value.eightSemMark.toDouble()) / 2

            val total = ygpa2 + (1.5 * ygpa3) + (1.5 * ygpa4)

            val average = total / 4
            val result = formateDouble(average)
            val percentage = calculatePercentage(average)

            _state.update {
                it.copy(result = result, percentage = percentage)
            }
            insert(
                DgpaMidSemMarks(
                    type = DgpaMidSemMarksTypes.DGPA_3_YEAR,
                    thirdSemGpa = state.value.thirdSemMark.toDouble(),
                    fourthSemGpa = state.value.fourthSemMark.toDouble(),
                    fifthSemGpa = state.value.fiveSemMark.toDouble(),
                    sixthSemGpa = state.value.sixSemMark.toDouble(),
                    seventhSemGpa = state.value.sevenSemMark.toDouble(),
                    eighthSemGpa = state.value.eightSemMark.toDouble(),

                    avgGpa = result?:0.0,
                    avgPercentage = percentage
                )
            )
        }catch (e: Exception){
            e.printStackTrace()
        }
    }


    fun clear(){
        _state.update {
            it.copy(
                firstSemMark = "",
                secondSemMark = "",
                thirdSemMark = "",
                fourthSemMark = "",
                fiveSemMark = "",
                sixSemMark = "",
                sevenSemMark = "",
                eightSemMark = "",
                result = null,
                percentage = null
            )
        }
    }

    fun updateState(state: DgpaScreenState){
        _state.update { state }
    }




}