package com.rkbapps.makautsgpaygpacalculator.ui.screens.dgpa

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarks
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarksTypes
import com.rkbapps.makautsgpaygpacalculator.ui.screens.home.AppTopBar
import com.rkbapps.makautsgpaygpacalculator.utils.calculatePercentage
import java.util.Locale

@Composable
fun DgpaCalculatorScreen(
    backStack: SnapshotStateList<Any>,
    viewModel: DgpaCalculatorViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val selectedItem = rememberSaveable {
        mutableStateOf(CourseType.FOUR_YEAR_DEGREE)
    }

    Scaffold(topBar = {
        AppTopBar(showBack = true) {
            backStack.removeLastOrNull()
        }
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Choose your course duration:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChooseCourseTypeItem(
                    selectedItem = selectedItem,
                    courseType = CourseType.FOUR_YEAR_DEGREE
                )
                ChooseCourseTypeItem(
                    selectedItem = selectedItem,
                    courseType = CourseType.THREE_YEAR_LATERAL
                )
                ChooseCourseTypeItem(
                    selectedItem = selectedItem,
                    courseType = CourseType.THREE_YEAR_DEGREE
                )
            }

            when (selectedItem.value) {

                CourseType.FOUR_YEAR_DEGREE -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        FourYearDegreeItem(context = context, viewModel = viewModel)
                    }
                }

                CourseType.THREE_YEAR_DEGREE -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        ThreeYearDegreeItem(context = context, viewModel = viewModel)
                    }
                }

                CourseType.THREE_YEAR_LATERAL -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        ThreeYearLateralItem(context = context, viewModel = viewModel)
                    }
                }

            }


        }
    }

}

object CourseType {
    const val THREE_YEAR_DEGREE = "3 year degree"
    const val THREE_YEAR_LATERAL = "3 year lateral"
    const val FOUR_YEAR_DEGREE = "4 year degree"
}

@Composable
fun ChooseCourseTypeItem(
    selectedItem: MutableState<String> = remember {
        mutableStateOf(CourseType.FOUR_YEAR_DEGREE)
    },
    courseType: String
) {
    Box(modifier = Modifier.padding(5.dp)) {
        if (courseType == selectedItem.value) {
            Card(modifier = Modifier.align(Alignment.Center)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 5.dp),
                ) {
                    Text(
                        text = courseType,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            OutlinedCard(
                onClick = {
                    selectedItem.value = courseType
                },
                modifier = Modifier.align(Alignment.Center)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 5.dp),
                ) {
                    Text(
                        text = courseType,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun SemesterSgpaInputField(
    value: MutableState<String>,
    modifier: Modifier,
    placeHolderText: String,
    semNumber: Int,

    ) {
    OutlinedTextField(
        value = value.value, onValueChange = {
            value.value = it
        },
        modifier = modifier,
        placeholder = {
            Text(text = "$placeHolderText Semester")
        },
        label = {
            Text(text = "Sem $semNumber")
        },
        supportingText = {
            Text(text = "Enter $placeHolderText semester SGPA.")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )

}


@Composable
fun FourYearDegreeItem(context: Context, viewModel: DgpaCalculatorViewModel) {

    val firstSemester = rememberSaveable {
        mutableStateOf("")
    }
    val secondSemester = rememberSaveable {
        mutableStateOf("")
    }
    val thirdSemester = rememberSaveable {
        mutableStateOf("")
    }
    val fourthSemester = rememberSaveable {
        mutableStateOf("")
    }
    val fifthSemester = rememberSaveable {
        mutableStateOf("")
    }
    val sixthSemester = rememberSaveable {
        mutableStateOf("")
    }
    val seventhSemester = rememberSaveable {
        mutableStateOf("")
    }
    val eighthSemester = rememberSaveable {
        mutableStateOf("")
    }

    val overallDgpa = rememberSaveable {
        mutableDoubleStateOf(0.0)
    }

    val averagePercentage = rememberSaveable {
        mutableDoubleStateOf(0.0)
    }
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Enter all eight semester SGPA : ",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SemesterSgpaInputField(
                value = firstSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "1st",
                semNumber = 1
            )
            Spacer(modifier = Modifier.width(5.dp))
            SemesterSgpaInputField(
                value = secondSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "2nd",
                semNumber = 2
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SemesterSgpaInputField(
                value = thirdSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "3rd",
                semNumber = 3
            )
            Spacer(modifier = Modifier.width(5.dp))
            SemesterSgpaInputField(
                value = fourthSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "4th",
                semNumber = 4
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SemesterSgpaInputField(
                value = fifthSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "5th",
                semNumber = 5
            )
            Spacer(modifier = Modifier.width(5.dp))
            SemesterSgpaInputField(
                value = sixthSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "6th",
                semNumber = 6
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SemesterSgpaInputField(
                value = seventhSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "7th",
                semNumber = 7
            )
            Spacer(modifier = Modifier.width(5.dp))
            SemesterSgpaInputField(
                value = eighthSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "8th",
                semNumber = 8
            )
        }

        Spacer(modifier = Modifier.height(8.dp))


        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                try {
                    if (firstSemester.value.isNotEmpty() &&
                        secondSemester.value.isNotEmpty() &&
                        thirdSemester.value.isNotEmpty() &&
                        fourthSemester.value.isNotEmpty() &&
                        fifthSemester.value.isNotEmpty() &&
                        sixthSemester.value.isNotEmpty() &&
                        seventhSemester.value.isNotEmpty() &&
                        eighthSemester.value.isNotEmpty()
                    ) {
                        val ygpa1 = (firstSemester.value.toDouble() +
                                secondSemester.value.toDouble()) / 2
                        val ygpa2 = (thirdSemester.value.toDouble() +
                                fourthSemester.value.toDouble()) / 2
                        val ygpa3 = (fifthSemester.value.toDouble() +
                                sixthSemester.value.toDouble()) / 2
                        val ygpa4 = (seventhSemester.value.toDouble() +
                                eighthSemester.value.toDouble()) / 2
                        val total = ygpa1 + ygpa2 + (1.5 * ygpa3) + (1.5 * ygpa4)
                        val average = total / 5
                        overallDgpa.doubleValue =
                            String.format(locale = Locale.getDefault(), "%.2f", average).toDouble()
                        averagePercentage.doubleValue = calculatePercentage(average)
                        viewModel.insert(
                            DgpaMidSemMarks(
                                type = DgpaMidSemMarksTypes.DGPA_4_YEAR,
                                firstSemGpa = firstSemester.value.toDouble(),
                                secondSemGpa = secondSemester.value.toDouble(),
                                thirdSemGpa = thirdSemester.value.toDouble(),
                                fourthSemGpa = fourthSemester.value.toDouble(),
                                fifthSemGpa = fifthSemester.value.toDouble(),
                                sixthSemGpa = sixthSemester.value.toDouble(),
                                seventhSemGpa = seventhSemester.value.toDouble(),
                                eighthSemGpa = eighthSemester.value.toDouble(),

                                avgGpa = overallDgpa.doubleValue,
                                avgPercentage = averagePercentage.doubleValue

                            )
                        )


                    } else {
                        //Toast
                        Toast.makeText(
                            context,
                            "Enter your all eight semester SGPA.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (_: Exception) {
                    //Toast
                    Toast.makeText(context, "Enter proper SGPA.", Toast.LENGTH_SHORT).show()
                }
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Calculate")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                firstSemester.value = ""
                secondSemester.value = ""
                thirdSemester.value = ""
                fourthSemester.value = ""
                fifthSemester.value = ""
                sixthSemester.value = ""
                seventhSemester.value = ""
                eighthSemester.value = ""
                overallDgpa.doubleValue = 0.0
                averagePercentage.doubleValue = 0.0
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Reset")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        AnimatedVisibility(overallDgpa.doubleValue > 0.0 && averagePercentage.doubleValue > 0.0) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    Text(
                        text = "DGPA : ${overallDgpa.doubleValue}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Overall Percentage : ${averagePercentage.doubleValue}%",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
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
                        text = "Enter all of your 8 semester SGPA in proper place and hit 'Calculate' button to calculate your overall percentage and  DGPA (Degree Grade Point).",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}

@Composable
fun ThreeYearLateralItem(context: Context, viewModel: DgpaCalculatorViewModel) {

    val thirdSemester = rememberSaveable {
        mutableStateOf("")
    }
    val fourthSemester = rememberSaveable {
        mutableStateOf("")
    }
    val fifthSemester = rememberSaveable {
        mutableStateOf("")
    }
    val sixthSemester = rememberSaveable {
        mutableStateOf("")
    }
    val seventhSemester = rememberSaveable {
        mutableStateOf("")
    }
    val eighthSemester = rememberSaveable {
        mutableStateOf("")
    }

    val overallDgpa = rememberSaveable {
        mutableDoubleStateOf(0.0)
    }

    val averagePercentage = rememberSaveable {
        mutableDoubleStateOf(0.0)
    }
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Enter all six semester SGPA : ",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SemesterSgpaInputField(
                value = thirdSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "3rd",
                semNumber = 3
            )
            Spacer(modifier = Modifier.width(5.dp))
            SemesterSgpaInputField(
                value = fourthSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "4th",
                semNumber = 4
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SemesterSgpaInputField(
                value = fifthSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "5th",
                semNumber = 5
            )
            Spacer(modifier = Modifier.width(5.dp))
            SemesterSgpaInputField(
                value = sixthSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "6th",
                semNumber = 6
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SemesterSgpaInputField(
                value = seventhSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "7th",
                semNumber = 7
            )
            Spacer(modifier = Modifier.width(5.dp))
            SemesterSgpaInputField(
                value = eighthSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "8th",
                semNumber = 8
            )
        }

        Spacer(modifier = Modifier.height(8.dp))


        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                try {
                    if (
                        thirdSemester.value.isNotEmpty() &&
                        fourthSemester.value.isNotEmpty() &&
                        fifthSemester.value.isNotEmpty() &&
                        sixthSemester.value.isNotEmpty() &&
                        seventhSemester.value.isNotEmpty() &&
                        eighthSemester.value.isNotEmpty()
                    ) {

                        val ygpa2 = (thirdSemester.value.toDouble() +
                                fourthSemester.value.toDouble()) / 2
                        val ygpa3 = (fifthSemester.value.toDouble() +
                                sixthSemester.value.toDouble()) / 2
                        val ygpa4 = (seventhSemester.value.toDouble() +
                                eighthSemester.value.toDouble()) / 2

                        val total = ygpa2 + (1.5 * ygpa3) + (1.5 * ygpa4)

                        val average = total / 4
                        overallDgpa.doubleValue =
                            String.format(Locale.getDefault(), "%.2f", average).toDouble()
                        averagePercentage.doubleValue = calculatePercentage(average)

                        viewModel.insert(
                            DgpaMidSemMarks(
                                type = DgpaMidSemMarksTypes.DGPA_3_YEAR,
                                thirdSemGpa = thirdSemester.value.toDouble(),
                                fourthSemGpa = fourthSemester.value.toDouble(),
                                fifthSemGpa = fifthSemester.value.toDouble(),
                                sixthSemGpa = sixthSemester.value.toDouble(),
                                seventhSemGpa = seventhSemester.value.toDouble(),
                                eighthSemGpa = eighthSemester.value.toDouble(),

                                avgGpa = overallDgpa.doubleValue,
                                avgPercentage = averagePercentage.doubleValue
                            )
                        )

                    } else {
                        //Toast
                        Toast.makeText(
                            context,
                            "Enter your all six semester SGPA.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (_: Exception) {
                    //Toast
                    Toast.makeText(context, "Enter proper SGPA.", Toast.LENGTH_SHORT).show()
                }
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Calculate")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                thirdSemester.value = ""
                fourthSemester.value = ""
                fifthSemester.value = ""
                sixthSemester.value = ""
                seventhSemester.value = ""
                eighthSemester.value = ""
                overallDgpa.doubleValue = 0.0
                averagePercentage.doubleValue = 0.0
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Reset")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        AnimatedVisibility(overallDgpa.doubleValue > 0.0 && averagePercentage.doubleValue > 0.0) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    Text(
                        text = "DGPA : ${overallDgpa.doubleValue}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Overall Percentage : ${averagePercentage.doubleValue}%",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
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
                        text = "Enter all of your 6 semester (start from 3rd semester to 8th semester) SGPA in proper place and hit 'Calculate' button to calculate your overall percentage and  DGPA (Degree Grade Point).",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }


}

@Composable
fun ThreeYearDegreeItem(context: Context, viewModel: DgpaCalculatorViewModel) {

    val firstSemester = rememberSaveable {
        mutableStateOf("")
    }
    val secondSemester = rememberSaveable {
        mutableStateOf("")
    }
    val thirdSemester = rememberSaveable {
        mutableStateOf("")
    }
    val fourthSemester = rememberSaveable {
        mutableStateOf("")
    }
    val fifthSemester = rememberSaveable {
        mutableStateOf("")
    }
    val sixthSemester = rememberSaveable {
        mutableStateOf("")
    }

    val overallDgpa = rememberSaveable {
        mutableDoubleStateOf(0.0)
    }

    val averagePercentage = rememberSaveable {
        mutableDoubleStateOf(0.0)
    }
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Enter all six semester SGPA : ",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SemesterSgpaInputField(
                value = firstSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "1st",
                semNumber = 1
            )
            Spacer(modifier = Modifier.width(5.dp))
            SemesterSgpaInputField(
                value = secondSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "2nd",
                semNumber = 2
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SemesterSgpaInputField(
                value = thirdSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "3rd",
                semNumber = 3
            )
            Spacer(modifier = Modifier.width(5.dp))
            SemesterSgpaInputField(
                value = fourthSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "4th",
                semNumber = 4
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SemesterSgpaInputField(
                value = fifthSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "5th",
                semNumber = 5
            )
            Spacer(modifier = Modifier.width(5.dp))
            SemesterSgpaInputField(
                value = sixthSemester,
                modifier = Modifier.weight(1f),
                placeHolderText = "6th",
                semNumber = 6
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                try {
                    if (firstSemester.value.isNotEmpty() &&
                        secondSemester.value.isNotEmpty() &&
                        thirdSemester.value.isNotEmpty() &&
                        fourthSemester.value.isNotEmpty() &&
                        fifthSemester.value.isNotEmpty() &&
                        sixthSemester.value.isNotEmpty()
                    ) {

                        val ygpa1 = (firstSemester.value.toDouble() +
                                secondSemester.value.toDouble()) / 2
                        val ygpa2 = (thirdSemester.value.toDouble() +
                                fourthSemester.value.toDouble()) / 2

                        val ygpa3 = (fifthSemester.value.toDouble() +
                                sixthSemester.value.toDouble()) / 2

                        val total = ygpa1 + ygpa2 + ygpa3

                        val average = total / 3
                        overallDgpa.doubleValue =
                            String.format(Locale.getDefault(), "%.2f", average).toDouble()
                        averagePercentage.doubleValue = calculatePercentage(average)

                        viewModel.insert(
                            DgpaMidSemMarks(
                                type = DgpaMidSemMarksTypes.DGPA_3_YEAR,
                                firstSemGpa = firstSemester.value.toDouble(),
                                secondSemGpa = secondSemester.value.toDouble(),
                                thirdSemGpa = thirdSemester.value.toDouble(),
                                fourthSemGpa = fourthSemester.value.toDouble(),
                                fifthSemGpa = fifthSemester.value.toDouble(),
                                sixthSemGpa = sixthSemester.value.toDouble(),

                                avgGpa = overallDgpa.doubleValue,
                                avgPercentage = averagePercentage.doubleValue

                            )
                        )
                    } else {
                        //Toast
                        Toast.makeText(
                            context,
                            "Enter your all six semester SGPA.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (_: Exception) {
                    //Toast
                    Toast.makeText(context, "Enter proper SGPA.", Toast.LENGTH_SHORT).show()
                }
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Calculate")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                firstSemester.value = ""
                secondSemester.value = ""
                thirdSemester.value = ""
                fourthSemester.value = ""
                fifthSemester.value = ""
                sixthSemester.value = ""
                overallDgpa.doubleValue = 0.0
                averagePercentage.doubleValue = 0.0
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Reset")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        AnimatedVisibility(overallDgpa.doubleValue > 0.0 && averagePercentage.doubleValue > 0.0) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    Text(
                        text = "DGPA : ${overallDgpa.doubleValue}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Overall Percentage : ${averagePercentage.doubleValue}%",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
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
                        text = "Enter all of your 6 semester SGPA in proper place and hit 'Calculate' button to calculate your overall percentage and  DGPA (Degree Grade Point).",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}










