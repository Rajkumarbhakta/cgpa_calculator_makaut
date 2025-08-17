package com.rkbapps.makautsgpaygpacalculator.ui.screens.dgpa

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rkbapps.makautsgpaygpacalculator.ui.composables.AppTopBar
import com.rkbapps.makautsgpaygpacalculator.ui.composables.ButtonRow
import com.rkbapps.makautsgpaygpacalculator.ui.composables.NotesCard

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DgpaCalculatorScreen(backStack: SnapshotStateList<Any>, viewModel: DgpaCalculatorViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(topBar = { AppTopBar(showBack = true) { backStack.removeLastOrNull() } }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Choose your course duration:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CourseType.entries.forEach { type ->
                    ToggleButton(
                        checked = (type == state.currentSelectedCourseType),
                        onCheckedChange = { checked ->
                            if (checked) {
                                val update = state.copy(currentSelectedCourseType = type)
                                viewModel.updateState(update)
                            }
                        }
                    ) {
                        Text(type.value)
                    }
                }
            }

            Column(
                Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(text = "Enter all semester SGPA : ", style = MaterialTheme.typography.titleMedium)
                AnimatedVisibility(state.currentSelectedCourseType == CourseType.FOUR_YEAR_DEGREE ||
                        state.currentSelectedCourseType == CourseType.THREE_YEAR_DEGREE) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SemesterSgpaInputField(
                            value = state.firstSemMark, modifier = Modifier.weight(1f),
                            placeHolderText = "1st",
                        ){
                            val update = state.copy(firstSemMark = it)
                            viewModel.updateState(update)
                        }
                        SemesterSgpaInputField(
                            value = state.secondSemMark, modifier = Modifier.weight(1f),
                            placeHolderText = "2nd",
                        ){
                            val update = state.copy(secondSemMark = it)
                            viewModel.updateState(update)
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SemesterSgpaInputField(
                        value = state.thirdSemMark, modifier = Modifier.weight(1f),
                        placeHolderText = "3rd",
                    ){
                        val update = state.copy(thirdSemMark = it)
                        viewModel.updateState(update)
                    }
                    SemesterSgpaInputField(
                        value = state.fourthSemMark, modifier = Modifier.weight(1f),
                        placeHolderText = "4th",
                    ){
                        val update = state.copy(fourthSemMark = it)
                        viewModel.updateState(update)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SemesterSgpaInputField(
                        value = state.fiveSemMark, modifier = Modifier.weight(1f),
                        placeHolderText = "5th",
                    ){
                        val update = state.copy(fiveSemMark = it)
                        viewModel.updateState(update)
                    }
                    SemesterSgpaInputField(
                        value = state.sixSemMark, modifier = Modifier.weight(1f),
                        placeHolderText = "6th",
                    ){
                        val update = state.copy(sixSemMark = it)
                        viewModel.updateState(update)
                    }
                }
                AnimatedVisibility(
                    state.currentSelectedCourseType == CourseType.FOUR_YEAR_DEGREE ||
                            state.currentSelectedCourseType == CourseType.THREE_YEAR_LATERAL
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SemesterSgpaInputField(
                            value = state.sevenSemMark, modifier = Modifier.weight(1f),
                            placeHolderText = "7th",
                        ){
                            val update = state.copy(sevenSemMark = it)
                            viewModel.updateState(update)
                        }
                        SemesterSgpaInputField(
                            value = state.eightSemMark, modifier = Modifier.weight(1f),
                            placeHolderText = "8th",
                        ){
                            val update = state.copy(eightSemMark = it)
                            viewModel.updateState(update)
                        }
                    }
                }
                ButtonRow(onReset = viewModel::clear) {
                    viewModel.calculateResult()
                }
                AnimatedVisibility((state.result?:0.0) > 0.0 && (state.percentage?:0.0) > 0.0) {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(8.dp)
                        ) {

                            Text(
                                text = "DGPA : ${state.result}",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "Overall Percentage : ${state.percentage}%",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )

                        }
                    }
                }


                NotesCard(when(state.currentSelectedCourseType){
                    CourseType.FOUR_YEAR_DEGREE ->{
                        "Enter all of your 8 semester SGPA in proper place and hit 'Calculate' button to calculate your overall percentage and  DGPA (Degree Grade Point)."
                    }
                    CourseType.THREE_YEAR_DEGREE->{
                        "Enter all of your 6 semester SGPA in proper place and hit 'Calculate' button to calculate your overall percentage and  DGPA (Degree Grade Point)."
                    }
                    CourseType.THREE_YEAR_LATERAL->{
                        "Enter all of your 6 semester (start from 3rd semester to 8th semester) SGPA in proper place and hit 'Calculate' button to calculate your overall percentage and  DGPA (Degree Grade Point)."
                    }
                })
            }
        }
    }

}

enum class CourseType(val value:String){
    FOUR_YEAR_DEGREE("4 year degree"),
    THREE_YEAR_DEGREE("3 year degree"),
    THREE_YEAR_LATERAL("3 year lateral"),

}


@Composable
fun SemesterSgpaInputField(
    value: String, modifier: Modifier, placeHolderText: String,
    onValueChange:(String)-> Unit
) {
    Column( modifier = modifier,) {
        Text("$placeHolderText Semester")
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
           modifier= Modifier.fillMaxWidth(),
            placeholder = { Text(text = "SGPA") },
//            supportingText = { Text(text = "Enter $placeHolderText semester SGPA.") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )
    }
}










