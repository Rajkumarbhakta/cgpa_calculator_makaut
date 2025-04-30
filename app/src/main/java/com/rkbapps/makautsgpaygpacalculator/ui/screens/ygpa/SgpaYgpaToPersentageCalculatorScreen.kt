package com.rkbapps.makautsgpaygpacalculator.ui.screens.ygpa

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rkbapps.makautsgpaygpacalculator.db.entity.GpaPercentage
import com.rkbapps.makautsgpaygpacalculator.ui.screens.home.AppTopBar
import com.rkbapps.makautsgpaygpacalculator.utils.calculatePercentage

@Composable
fun SgpaYgpaPercentageCalculatorScreen(navController: NavHostController) {

    val context = LocalContext.current
    val viewModel:SgpaYgpaPercentageViewModel = hiltViewModel()
    val cgpaYgpa = rememberSaveable {
        mutableStateOf("")
    }
    val percentage = rememberSaveable {
        mutableDoubleStateOf(0.0)
    }

    Scaffold(topBar = {
        AppTopBar(showBack = true) {
            navController.navigateUp()
        }
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            if (cgpaYgpa.value.isEmpty()) {
                percentage.doubleValue = 0.0
            }
            Text(
                text = "Your SGPA/YGPA :",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = cgpaYgpa.value,
                onValueChange = { cgpa ->
                    cgpaYgpa.value = cgpa
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row {

                    Button(onClick = {

                        try {
                            if (cgpaYgpa.value.isNotEmpty() && cgpaYgpa.value.toDouble() <= 10) {
                                percentage.doubleValue =
                                    calculatePercentage(cgpaYgpa.value.toDouble())

                                viewModel.insert(GpaPercentage(
                                    gpa = cgpaYgpa.value.toDouble(),
                                    percentage = percentage.doubleValue
                                ))

                            } else {
                                Toast.makeText(context, "Enter CGPA/YGPA.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } catch (e: Exception) {
                            Toast.makeText(context, "Enter proper CGPA/YGPA.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }, modifier = Modifier.weight(1f)) {
                        Text(text = "Calculate")
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        cgpaYgpa.value = ""
                        percentage.doubleValue = 0.0
                    }, modifier = Modifier.weight(1f)) {
                        Text(text = "Reset")
                    }

                }
            }

            if (percentage.doubleValue > 0.0) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Card {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(text = "Your percentage : ${percentage.doubleValue}%")

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
                            text = "Convert your CGPA / YGPA / SGPA to percentage.Put your CGPA and press the calculate button to get the percentage value.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {

                Card {
                    Column(Modifier.padding(5.dp)) {
                        OutlinedCard {
                            Text(
                                text = "Makaut Ten Point Scale",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            OutlinedCard(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Grade Point",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            OutlinedCard(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Percentage",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp),
                                    textAlign = TextAlign.Center
                                )
                            }


                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        GradeScaleShowItem(grade = "6.25", percentage = "55")
                        Spacer(modifier = Modifier.height(2.dp))
                        GradeScaleShowItem(grade = "6.75", percentage = "60")
                        Spacer(modifier = Modifier.height(2.dp))
                        GradeScaleShowItem(grade = "7.25", percentage = "65")
                        Spacer(modifier = Modifier.height(2.dp))
                        GradeScaleShowItem(grade = "7.75", percentage = "70")
                        Spacer(modifier = Modifier.height(2.dp))
                        GradeScaleShowItem(grade = "8.25", percentage = "75")
                        Spacer(modifier = Modifier.height(2.dp))
                        GradeScaleShowItem(grade = "8.75", percentage = "80")

                    }
                }

            }
        }
    }
}

@Composable
fun GradeScaleShowItem(grade: String, percentage: String) {

    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        OutlinedCard(modifier = Modifier.weight(1f)) {
            Text(
                text = grade,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.width(5.dp))

        OutlinedCard(modifier = Modifier.weight(1f)) {
            Text(
                text = percentage,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textAlign = TextAlign.Center
            )
        }
    }


}