package com.rkbapps.makautsgpaygpacalculator.ui.screens.yearly

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rkbapps.makautsgpaygpacalculator.ui.composables.AppTopBar
import com.rkbapps.makautsgpaygpacalculator.ui.composables.ButtonRow
import com.rkbapps.makautsgpaygpacalculator.ui.composables.NotesCard

@Composable
fun YearlyMarksConverterScreen(
    backStack: SnapshotStateList<Any>,
    viewModel: YearlyMarksViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()


    Scaffold(topBar = {
        AppTopBar(showBack = true) {
            backStack.removeLastOrNull()
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            if (state.oddSemSgpa.isEmpty()) {
                val update = state.copy(oddSemPercentage="", yearPercentage="")
                viewModel.update(update)
            }
            if (state.oddSemTotalSubject.isEmpty()) {
                val update = state.copy(
                    oddSemTotalNumber="",yearTotalNumber=""
                )
                viewModel.update(update)
            }
            if (state.evenSemSgpa.isEmpty()) {
                val update = state.copy(
                    evenSemPercentage="",yearPercentage=""
                )
                viewModel.update(update)
            }
            if (state.evenSemTotalSubject.isEmpty()) {
                val update = state.copy(
                    evenSemTotalNumber="",yearTotalNumber=""
                )
                viewModel.update(update)
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
                        totalSubject = state.oddSemTotalSubject,
                        sgpa = state.oddSemSgpa,
                        onTotalSubValueChange = {
                            val update = state.copy(oddSemTotalSubject = it)
                            viewModel.update(update)
                        },
                        onSgpaValueChange = {
                            val update = state.copy(oddSemSgpa = it)
                            viewModel.update(update)
                        }
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                ) {
                    SemesterInputItem(
                        title = "Even Sem",
                        totalSubject = state.evenSemTotalSubject,
                        sgpa = state.evenSemSgpa,
                        onTotalSubValueChange = {
                            val update = state.copy(evenSemTotalSubject = it)
                            viewModel.update(update)
                        },
                        onSgpaValueChange = {
                            val update = state.copy(evenSemSgpa = it)
                            viewModel.update(update)
                        }
                    )
                }
            }


            ButtonRow(
                onReset = viewModel::clear,
                onCalculate = viewModel::calculate
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                AnimatedVisibility(state.oddSemPercentage.isNotEmpty()
                        && state.oddSemTotalNumber.isNotEmpty()
                        && state.oddSemObtainedNumber.isNotEmpty(),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp)
                    ) {
                        ResultCardItem(
                            title = "Odd Sem",
                            obtainedNumber = state.oddSemObtainedNumber,
                            totalNumber = state.oddSemTotalNumber,
                            percentage = "${state.oddSemPercentage}%"
                        )
                    }
                }
                AnimatedVisibility(state.evenSemObtainedNumber.isNotEmpty()
                        && state.evenSemPercentage.isNotEmpty()
                        && state.evenSemTotalNumber.isNotEmpty(),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp)
                    ) {
                        ResultCardItem(
                            title = "Even Sem",
                            obtainedNumber = state.evenSemObtainedNumber,
                            totalNumber = state.evenSemTotalNumber,
                            percentage = "${state.evenSemPercentage}%"
                        )
                    }
                }
            }

            AnimatedVisibility(state.yearPercentage.isNotEmpty() && state.yearObtainedNumber.isNotEmpty() && state.yearTotalNumber.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    ResultCardItem(
                        title = "Year Result",
                        obtainedNumber = state.yearObtainedNumber,
                        totalNumber = state.yearTotalNumber,
                        percentage = "${state.yearPercentage}%"
                    )
                }
            }

            NotesCard("Enter even an odd semester SGPA and total number of subjects (total theory subjects + total practical subjects) to calculate your total marks, obtained marks, and percentage.")
        }
    }
}


@Composable
fun SemesterInputItem(
    title: String,
    totalSubject: String,
    sgpa: String,
    onTotalSubValueChange:(String)-> Unit,
    onSgpaValueChange:(String)->Unit
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
                value = totalSubject, onValueChange = onTotalSubValueChange,
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
                value = sgpa, onValueChange = onSgpaValueChange,
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



