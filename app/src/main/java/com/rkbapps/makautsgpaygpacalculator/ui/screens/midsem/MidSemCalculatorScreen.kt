package com.rkbapps.makautsgpaygpacalculator.ui.screens.midsem

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarks
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarksTypes
import com.rkbapps.makautsgpaygpacalculator.ui.composables.AppTopBar
import com.rkbapps.makautsgpaygpacalculator.ui.composables.ButtonRow
import com.rkbapps.makautsgpaygpacalculator.utils.calculatePercentage
import java.util.Locale


@Composable
fun MidSemCalculatorScreen(
    backStack: SnapshotStateList<Any>,
    viewModel: MidSemCalculatorViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    Scaffold(topBar = {
        AppTopBar(showBack = true) {
            backStack.removeLastOrNull()
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
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
            Spacer(modifier = Modifier.height(5.dp))

            SemesterNumberFromItem(
                semester = "1st Semester",
                cgpa = state.firstSemCgpa
            ) {
                val update = state.copy(firstSemCgpa = it)
                viewModel.update(update)
            }
            SemesterNumberFromItem(
                semester = "2nd Semester",
                cgpa = state.secondSemCgpa
            ) {
                val update = state.copy(secondSemCgpa = it)
                viewModel.update(update)
            }
            SemesterNumberFromItem(
                semester = Options.THIRD_SEM.value,
                cgpa = state.thirdSemCgpa
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
                SemesterNumberFromItem(
                    semester = Options.FOURTH_SEM.value,
                    cgpa = state.fourthSemCgpa
                ){
                    val update = state.copy(fourthSemCgpa = it)
                    viewModel.update(update)
                }
            }

            AnimatedVisibility  (
                state.selectedOption==Options.FIFTH_SEM
                        || state.selectedOption == Options.SIXTH_SEM
                        || state.selectedOption == Options.SEVENTH_SEM
            ) {
                SemesterNumberFromItem(
                    semester = Options.FIFTH_SEM.value,
                    cgpa = state.fifthSemCgpa
                ){
                    val update = state.copy(fifthSemCgpa = it)
                    viewModel.update(update)
                }
            }

            AnimatedVisibility  (
                state.selectedOption == Options.SIXTH_SEM
                        || state.selectedOption == Options.SEVENTH_SEM
            ) {
                SemesterNumberFromItem(
                    semester = Options.SIXTH_SEM.value,
                    cgpa = state.sixthSemCgpa
                ){
                    val update = state.copy(sixthSemCgpa = it)
                    viewModel.update(update)
                }
            }

            AnimatedVisibility  (state.selectedOption == Options.SEVENTH_SEM) {
                SemesterNumberFromItem(
                    semester = Options.SEVENTH_SEM.value,
                    cgpa = state.seventhSemCgpa
                ){
                    val update = state.copy(seventhSemCgpa = it)
                    viewModel.update(update)
                }
            }


            ButtonRow(
                onReset = { viewModel.clear()}
            ) { viewModel.calculate() }

            AnimatedVisibility  (state.totalGpa > 0.0 && state.percentage > 0.0) {
                Card {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {

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


            Box(modifier = Modifier.fillMaxWidth()
            ) {

                OutlinedCard {

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {

                        Text(
                            text = "Notes",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Calculate your average(GPA) and percentage in the middle of the semester.First choose the semester up to which semester you want to calculate and then put your GPA accordingly. ",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun SemesterNumberFromItem(
    semester: String = "",
    cgpa: String,
    onChange:(String)->Unit
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = semester, style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.width(5.dp))
        OutlinedTextField(
            value = cgpa,
            onValueChange = onChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f),
            singleLine = true
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