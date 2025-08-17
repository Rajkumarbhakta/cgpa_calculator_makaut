package com.rkbapps.makautsgpaygpacalculator.ui.screens.midsem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rkbapps.makautsgpaygpacalculator.ui.composables.AppTopBar
import com.rkbapps.makautsgpaygpacalculator.ui.composables.ButtonRow
import com.rkbapps.makautsgpaygpacalculator.ui.composables.NotesCard


@Composable
fun MidSemCalculatorScreen(
    backStack: SnapshotStateList<Any>,
    viewModel: MidSemCalculatorViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    Scaffold(topBar = {
        AppTopBar(showBack = true) { backStack.removeLastOrNull() }
    }) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxWidth().padding(innerPadding)
                .padding(vertical = 8.dp, horizontal = 16.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Text(
                text = "Choose Semester Up To :",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                ExposedDropdownMenuSample(
                    options = Options.entries,
                    selectedOptionText = state.selectedOption,
                ){
                    val update = state.copy(selectedOption = it)
                    viewModel.update(update)
                }
            }

            Text(
                text = "Put GPA : ",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
            )

            SemesterNumberFromItem(semester = "1st Semester", cgpa = state.firstSemCgpa) {
                val update = state.copy(firstSemCgpa = it)
                viewModel.update(update)
            }
            SemesterNumberFromItem(semester = "2nd Semester", cgpa = state.secondSemCgpa) {
                val update = state.copy(secondSemCgpa = it)
                viewModel.update(update)
            }
            SemesterNumberFromItem(semester = Options.THIRD_SEM.value, cgpa = state.thirdSemCgpa
            ) {
                val update = state.copy(thirdSemCgpa = it)
                viewModel.update(update)
            }


            AnimatedVisibility (
                    state.selectedOption == Options.FOURTH_SEM
                        || state.selectedOption == Options.FIFTH_SEM
                        || state.selectedOption == Options.SIXTH_SEM
                        || state.selectedOption == Options.SEVENTH_SEM
            ) {
                SemesterNumberFromItem(semester = Options.FOURTH_SEM.value, cgpa = state.fourthSemCgpa){
                    val update = state.copy(fourthSemCgpa = it)
                    viewModel.update(update)
                }
            }

            AnimatedVisibility  (
                state.selectedOption==Options.FIFTH_SEM
                        || state.selectedOption == Options.SIXTH_SEM
                        || state.selectedOption == Options.SEVENTH_SEM
            ) {
                SemesterNumberFromItem(semester = Options.FIFTH_SEM.value, cgpa = state.fifthSemCgpa){
                    val update = state.copy(fifthSemCgpa = it)
                    viewModel.update(update)
                }
            }

            AnimatedVisibility  (
                state.selectedOption == Options.SIXTH_SEM
                        || state.selectedOption == Options.SEVENTH_SEM
            ) {
                SemesterNumberFromItem(semester = Options.SIXTH_SEM.value, cgpa = state.sixthSemCgpa){
                    val update = state.copy(sixthSemCgpa = it)
                    viewModel.update(update)
                }
            }

            AnimatedVisibility  (state.selectedOption == Options.SEVENTH_SEM) {
                SemesterNumberFromItem(semester = Options.SEVENTH_SEM.value, cgpa = state.seventhSemCgpa){
                    val update = state.copy(seventhSemCgpa = it)
                    viewModel.update(update)
                }
            }


            ButtonRow(
                onReset = { viewModel.clear()}
            ) { viewModel.calculate() }

            AnimatedVisibility  (state.totalGpa > 0.0 && state.percentage > 0.0) {
                Card {
                    Column(modifier =  Modifier.fillMaxWidth().padding(8.dp)) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Average CGPA : ",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "${state.totalGpa}")
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Average Percentage : ",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "${state.percentage}%")
                        }

                    }
                }
            }


            NotesCard(
                text = "Calculate your average(GPA) and percentage in the middle of the semester.First choose the semester up to which semester you want to calculate and then put your GPA accordingly. "
            )
        }
    }
}


@Composable
fun SemesterNumberFromItem(
    semester: String = "",
    cgpa: String,
    onChange:(String)->Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = semester, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal))
        OutlinedTextField(
            value = cgpa,
            onValueChange = onChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            placeholder = {Text("SGPA")},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )
    }
}

enum class Options(val value:String,val count:Int) {
    THIRD_SEM("3rd Semester",3),
    FOURTH_SEM("4th Semester",4),
    FIFTH_SEM("5th Semester",5),
    SIXTH_SEM("6th Semester",6),
    SEVENTH_SEM("7th Semester",7),
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuSample(
    options: List<Options>,
    selectedOptionText: Options = Options.THIRD_SEM,
    onChange:(Options)-> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = { expanded.value = !expanded.value },
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true).fillMaxWidth(),
            readOnly = true,
            value = selectedOptionText.value,
            onValueChange = {},
            shape = RoundedCornerShape(50f),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )
        ExposedDropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            shape = RoundedCornerShape(50f)
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption.value) },
                    onClick = {
                        onChange(selectionOption)
                        expanded.value = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )

            }
        }
    }
}