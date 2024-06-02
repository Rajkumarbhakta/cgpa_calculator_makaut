package com.rkbapps.makautsgpaygpacalculator.components

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
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rkbapps.makautsgpaygpacalculator.utils.calculateObtainedNumber
import com.rkbapps.makautsgpaygpacalculator.utils.calculatePercentage
import com.rkbapps.makautsgpaygpacalculator.utils.calculateTotalNumber
import kotlin.math.sin

@Composable
fun YearlyMarksConverterScreen(navController: NavHostController) {
    val context = LocalContext.current
    val oddSemTotalSubject = rememberSaveable {
        mutableStateOf("")
    }
    val oddSemSgpa = rememberSaveable {
        mutableStateOf("")
    }
    val evenSemTotalSubject = rememberSaveable {
        mutableStateOf("")
    }
    val evenSemSgpa = rememberSaveable {
        mutableStateOf("")
    }

    val oddSemObtainedNumber = rememberSaveable {
        mutableStateOf("")
    }
    val oddSemTotalNumber = rememberSaveable {
        mutableStateOf("")
    }
    val oddSemPercentage = rememberSaveable {
        mutableStateOf("")
    }
    val evenSemObtainedNumber = rememberSaveable {
        mutableStateOf("")
    }
    val evenSemTotalNumber = rememberSaveable {
        mutableStateOf("")
    }
    val evenSemPercentage = rememberSaveable {
        mutableStateOf("")
    }

    val yearObtainedNumber = rememberSaveable {
        mutableStateOf("")
    }
    val yearTotalNumber = rememberSaveable {
        mutableStateOf("")
    }
    val yearPercentage = rememberSaveable {
        mutableStateOf("")
    }


    Scaffold(topBar = {
        HomeScreenTopBar(showBack = true) {
            navController.navigateUp()
        }
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {

            if (oddSemSgpa.value.isEmpty()) {
                oddSemPercentage.value = ""
                yearPercentage.value = ""
            }
            if (oddSemTotalSubject.value.isEmpty()) {
                oddSemTotalNumber.value = ""
                yearTotalNumber.value = ""
            }
            if (evenSemSgpa.value.isEmpty()) {
                evenSemPercentage.value = ""
                yearPercentage.value = ""
            }
            if (evenSemTotalSubject.value.isEmpty()) {
                evenSemTotalNumber.value = ""
                yearTotalNumber.value = ""
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                ) {
                    SemesterInputItem(
                        title = "Odd Sem",
                        totalSubject = oddSemTotalSubject,
                        sgpa = oddSemSgpa,
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                ) {
                    SemesterInputItem(
                        title = "Even Sem",
                        totalSubject = evenSemTotalSubject,
                        sgpa = evenSemSgpa,
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {

                Button(onClick = {
                    try {
                        if (oddSemTotalSubject.value.isNotEmpty() && oddSemSgpa.value.isNotEmpty()) {
                            if (oddSemSgpa.value.toDouble() <= 10) {
                                try {
                                    oddSemTotalNumber.value =
                                        calculateTotalNumber(oddSemTotalSubject.value.toInt()).toString()
                                    oddSemPercentage.value =
                                        calculatePercentage(oddSemSgpa.value.toDouble()).toString()
                                    oddSemObtainedNumber.value = calculateObtainedNumber(
                                        oddSemTotalSubject.value.toInt(),
                                        oddSemPercentage.value.toDouble()
                                    ).toString()

                                } catch (e: Exception) {
                                    Toast.makeText(
                                        context,
                                        "Please provide proper odd sem details.",
                                        Toast.LENGTH_SHORT
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
//                        else{
//                            Toast.makeText(context, "Please provide proper details.", Toast.LENGTH_SHORT).show()
//                        }

                        if (evenSemTotalSubject.value.isNotEmpty() && evenSemSgpa.value.isNotEmpty()) {
                            if (evenSemSgpa.value.toDouble() <= 10) {
                                try {
                                    evenSemTotalNumber.value =
                                        calculateTotalNumber(evenSemTotalSubject.value.toInt()).toString()
                                    evenSemPercentage.value =
                                        calculatePercentage(evenSemSgpa.value.toDouble()).toString()
                                    evenSemObtainedNumber.value = calculateObtainedNumber(
                                        evenSemTotalSubject.value.toInt(),
                                        evenSemPercentage.value.toDouble()
                                    ).toString()
                                } catch (e: Exception) {
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
//                        else{
//                            Toast.makeText(context, "Please provide proper details.", Toast.LENGTH_SHORT).show()
//                        }

                        if (oddSemTotalSubject.value.isNotEmpty() && evenSemTotalSubject.value.isNotEmpty() && oddSemSgpa.value.isNotEmpty() && evenSemSgpa.value.isNotEmpty()) {

                            try {
                                val totalSubjects =
                                    oddSemTotalSubject.value.toInt() + evenSemTotalSubject.value.toInt()
                                yearTotalNumber.value =
                                    calculateTotalNumber(totalSubjects).toString()
                                yearPercentage.value =
                                    calculatePercentage((oddSemSgpa.value.toDouble() + evenSemSgpa.value.toDouble()) / 2.0).toString()
                                yearObtainedNumber.value = calculateObtainedNumber(
                                    totalSubjects,
                                    yearPercentage.value.toDouble()
                                ).toString()
                            } catch (e: Exception) {
                                Toast.makeText(
                                    context,
                                    "Please provide proper details.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
//                        else{
//                            Toast.makeText(context, "Please provide proper details.", Toast.LENGTH_SHORT).show()
//                        }


                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "Something went wrong.Please provide details properly.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                }, modifier = Modifier.weight(1f)) {
                    Text(text = "Calculate")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = {
                    oddSemTotalSubject.value = ""
                    oddSemSgpa.value = ""
                    evenSemTotalSubject.value = ""
                    evenSemSgpa.value = ""
                    oddSemTotalNumber.value = ""
                    oddSemPercentage.value = ""
                    oddSemObtainedNumber.value = ""
                    evenSemTotalNumber.value = ""
                    evenSemPercentage.value = ""
                    evenSemObtainedNumber.value = ""
                    yearTotalNumber.value = ""
                    yearPercentage.value = ""
                    yearObtainedNumber.value = ""
                }, modifier = Modifier.weight(1f)) {
                    Text(text = "Reset")
                }
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                if (oddSemPercentage.value.isNotEmpty() && oddSemTotalNumber.value.isNotEmpty() && oddSemObtainedNumber.value.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp)
                    ) {
                        ResultCardItem(
                            title = "Odd Sem",
                            obtainedNumber = oddSemObtainedNumber.value,
                            totalNumber = oddSemTotalNumber.value,
                            percentage = "${oddSemPercentage.value}%"
                        )
                    }
                }
                if (evenSemObtainedNumber.value.isNotEmpty() && evenSemPercentage.value.isNotEmpty() && evenSemTotalNumber.value.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp)
                    ) {
                        ResultCardItem(
                            title = "Even Sem",
                            obtainedNumber = evenSemObtainedNumber.value,
                            totalNumber = evenSemTotalNumber.value,
                            percentage = "${evenSemPercentage.value}%"
                        )
                    }
                }
            }

            if (yearPercentage.value.isNotEmpty() && yearObtainedNumber.value.isNotEmpty() && yearTotalNumber.value.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    ResultCardItem(
                        title = "Year Result",
                        obtainedNumber = yearObtainedNumber.value,
                        totalNumber = yearTotalNumber.value,
                        percentage = "${yearPercentage.value}%"
                    )
                }
            }

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
                            text = "Enter even an odd semester SGPA and total number of subjects (total theory subjects + total practical subjects) to calculate your total marks, obtained marks, and percentage.",
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
fun SemesterInputItem(
    title: String,
    totalSubject: MutableState<String>,
    sgpa: MutableState<String>,
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = totalSubject.value, onValueChange = {
                    totalSubject.value = it
                },
                placeholder = { Text(text = "0") },
                supportingText = {
                    Text(
                        text = "Enter total subjects including practical subjects.",
                    )
                }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = sgpa.value, onValueChange = {
                    sgpa.value = it
                },
                placeholder = { Text(text = "0.0") },
                supportingText = {
                    Text(
                        text = "Enter your semester SGPA.",
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }
    }
}

@Composable
fun ResultCardItem(
    title: String,
    obtainedNumber: String,
    totalNumber: String,
    percentage: String
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Percentage :", style = MaterialTheme.typography.titleSmall)
            SelectionContainer {
                Text(text = percentage, fontWeight = FontWeight.Bold)
            }
            Text(text = "Mark :", style = MaterialTheme.typography.titleSmall)
            SelectionContainer {
                Text(text = "$obtainedNumber out of $totalNumber", fontWeight = FontWeight.Bold)
            }
        }
    }
}



