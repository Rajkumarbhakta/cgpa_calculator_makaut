package com.rkbapps.makautsgpaygpacalculator.screens.midsem

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rkbapps.makautsgpaygpacalculator.screens.home.HomeScreenTopBar
import com.rkbapps.makautsgpaygpacalculator.utils.calculatePercentage


@Composable
fun MidSemCalculatorScreen(navController: NavHostController) {

    val context = LocalContext.current

    val selectedIndexText = rememberSaveable {
        mutableStateOf(Options.optionsList[0])
    }
    val firstSemCgpa = rememberSaveable {
        mutableStateOf("")
    }

    val secondSemCgpa = rememberSaveable {
        mutableStateOf("")
    }
    val thirdSemCgpa = rememberSaveable {
        mutableStateOf("")
    }
    val fourthSemCgpa = rememberSaveable {
        mutableStateOf("")
    }
    val fifthSemCgpa = rememberSaveable {
        mutableStateOf("")
    }
    val sixthSemCgpa = rememberSaveable {
        mutableStateOf("")
    }
    val seventhSemCgpa = rememberSaveable {
        mutableStateOf("")
    }

    val totalCgpa = rememberSaveable {
        mutableDoubleStateOf(0.0)
    }

    val totalPercentage = rememberSaveable {
        mutableDoubleStateOf(0.0)
    }

    val count = rememberSaveable {
        mutableIntStateOf(3)
    }



    Scaffold(topBar = {
        HomeScreenTopBar(showBack = true) {
            navController.navigateUp()
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {

            when (selectedIndexText.value) {

                Options.THIRD_SEM -> {
                    count.intValue = 3
                    fourthSemCgpa.value = ""
                    fifthSemCgpa.value = ""
                    sixthSemCgpa.value = ""
                    seventhSemCgpa.value = ""
                }

                Options.FOURTH_SEM -> {
                    count.intValue = 4
                    fifthSemCgpa.value = ""
                    sixthSemCgpa.value = ""
                    seventhSemCgpa.value = ""
                }

                Options.FIFTH_SEM -> {
                    count.intValue = 5
                    sixthSemCgpa.value = ""
                    seventhSemCgpa.value = ""
                }

                Options.SIXTH_SEM -> {
                    count.intValue = 6
                    seventhSemCgpa.value = ""
                }

                Options.SEVENTH_SEM -> {
                    count.intValue = 7
                }
            }
            Text(
                text = "Choose Semester Up To :",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 2.dp)
            ) {
                ExposedDropdownMenuSample(
                    options = Options.optionsList,
                    selectedOptionText = selectedIndexText
                )
            }

            Text(
                text = "Put GPA : ",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))

            if (count.intValue >= 3) {
                SemesterNumberFromItem(
                    semester = "1st Semester",
                    cgpa = firstSemCgpa
                )
                SemesterNumberFromItem(
                    semester = "2nd Semester",
                    cgpa = secondSemCgpa
                )
                SemesterNumberFromItem(
                    semester = Options.THIRD_SEM,
                    cgpa = thirdSemCgpa
                )
            }

            if (count.intValue >= 4) {
                SemesterNumberFromItem(
                    semester = Options.FOURTH_SEM,
                    cgpa = fourthSemCgpa
                )
            }

            if (count.intValue >= 5) {
                SemesterNumberFromItem(
                    semester = Options.FIFTH_SEM,
                    cgpa = fifthSemCgpa
                )
            }

            if (count.intValue >= 6) {
                SemesterNumberFromItem(
                    semester = Options.SIXTH_SEM,
                    cgpa = sixthSemCgpa
                )
            }

            if (count.intValue >= 7) {
                SemesterNumberFromItem(
                    semester = Options.SEVENTH_SEM,
                    cgpa = seventhSemCgpa
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Button(onClick = {
                    try {
                        val first =
                            if (firstSemCgpa.value.isEmpty()) 0.0 else firstSemCgpa.value.toDouble()
                        val second =
                            if (secondSemCgpa.value.isEmpty()) 0.0 else secondSemCgpa.value.toDouble()
                        val third =
                            if (thirdSemCgpa.value.isEmpty()) 0.0 else thirdSemCgpa.value.toDouble()
                        val fourth =
                            if (fourthSemCgpa.value.isEmpty()) 0.0 else fourthSemCgpa.value.toDouble()
                        val fifth =
                            if (fifthSemCgpa.value.isEmpty()) 0.0 else fifthSemCgpa.value.toDouble()
                        val sixth =
                            if (sixthSemCgpa.value.isEmpty()) 0.0 else sixthSemCgpa.value.toDouble()
                        val seventh =
                            if (seventhSemCgpa.value.isEmpty()) 0.0 else seventhSemCgpa.value.toDouble()
                        val total = first + second + third + fourth + fifth + sixth + seventh
//                        Log.d("Number_Calculation","first $first second $second third $third fourth $fourth five $fifth six $sixth seven $seventh")
//                        Log.d("Number_Calculation","total : $total count : ${count.intValue}")
                        totalCgpa.doubleValue =
                            String.format("%.2f", total / count.intValue.toDouble()).toDouble()
                        totalPercentage.doubleValue =
                            calculatePercentage(total / count.intValue.toDouble())
                    } catch (e: Exception) {
                        Toast.makeText(context, "Please enter CGPA properly.", Toast.LENGTH_SHORT)
                            .show()
                    }

                }, modifier = Modifier.weight(1f)) {
                    Text(text = "Calculate")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    firstSemCgpa.value = ""
                    secondSemCgpa.value = ""
                    thirdSemCgpa.value = ""
                    fourthSemCgpa.value = ""
                    fifthSemCgpa.value = ""
                    sixthSemCgpa.value = ""
                    seventhSemCgpa.value = ""
                    totalPercentage.doubleValue = 0.0
                    totalCgpa.doubleValue = 0.0
                }, modifier = Modifier.weight(1f)) {
                    Text(text = "Reset")
                }
            }

            if (totalCgpa.doubleValue > 0.0 && totalPercentage.doubleValue > 0.0) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
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
                            Text(text = "${totalCgpa.doubleValue}")
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
                            Text(text = "${totalPercentage.doubleValue}%")
                        }

                    }
                }
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
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
fun SemesterNumberFromItem(semester: String = "", cgpa: MutableState<String>) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = semester, style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.width(5.dp))
        OutlinedTextField(
            value = cgpa.value,
            onValueChange = {
                cgpa.value = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f),
            singleLine = true
        )
    }
}

object Options {
    const val THIRD_SEM = "3rd Semester"
    const val FOURTH_SEM = "4th Semester"
    const val FIFTH_SEM = "5th Semester"
    const val SIXTH_SEM = "6th Semester"
    const val SEVENTH_SEM = "7th Semester"

    val optionsList = listOf<String>(
        THIRD_SEM,
        FOURTH_SEM,
        FIFTH_SEM,
        SIXTH_SEM,
        SEVENTH_SEM
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuSample(
    options: List<String> = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5"),
    selectedOptionText: MutableState<String> = remember { mutableStateOf(options[0]) },
) {
    val expanded = remember { mutableStateOf(false) }
    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = { expanded.value = !expanded.value },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = selectedOptionText.value,
            onValueChange = {},
            label = { Text("Choose Semester") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedOptionText.value = selectionOption
                        expanded.value = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}