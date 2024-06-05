package com.rkbapps.makautsgpaygpacalculator.screens.history.yearly

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rkbapps.makautsgpaygpacalculator.db.entity.YearlyMarks
import com.rkbapps.makautsgpaygpacalculator.screens.home.HomeScreenTopBar
import com.rkbapps.makautsgpaygpacalculator.utils.Constant
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun YearlyMarksHistoryScreen(navController:NavHostController) {
    val viewModel:YearlyMarksHistoryViewModel = hiltViewModel()
    val yearlyMarksHistoryList = viewModel.yearlyCalculatedMarks.collectAsState()

    val isDescOrdered = rememberSaveable {
        mutableStateOf(true)
    }

    val isClearHistoryDialogVisible = rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = isDescOrdered.value) {
        if (isDescOrdered.value){
            viewModel.getAllSortedDesc()
        }else{
            viewModel.getAllSortedAsc()
        }
    }

    Scaffold(
        topBar = {
            HomeScreenTopBar(showBack = true){
                navController.navigateUp()
            }
        }
    ) {paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp,),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = "History", style = MaterialTheme.typography.titleLarge)
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = {
                        isDescOrdered.value = !isDescOrdered.value
                    }) {
                        Icon(imageVector = if (isDescOrdered.value)Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp, contentDescription = "ace and dec")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    IconButton(onClick = {
                        isClearHistoryDialogVisible.value = true
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "clear history")
                    }
                }
            }


            if (yearlyMarksHistoryList.value.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp,)
                ) {
                    items(count = yearlyMarksHistoryList.value.size, key = {
                        yearlyMarksHistoryList.value[it].id
                    }){
                        YearlyMarksHistoryItem(yearlyMarks = yearlyMarksHistoryList.value[it]){
                            viewModel.deleteYearlyMarks(yearlyMarksHistoryList.value[it])
                        }
                    }
                }

            }else{
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp), contentAlignment = Alignment.Center){
                    Text(text = "You have no history yet.")
                }
            }

            if (isClearHistoryDialogVisible.value){
                AlertDialog(
                    onDismissRequest = { isClearHistoryDialogVisible.value = false },
                    title = { Text(text = "Clear History") },
                    text = { Text(text = "Are you sure you want to clear history?") },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.clearHistory()
                            isClearHistoryDialogVisible.value = false
                        }) {
                            Text(text = "Delete")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { isClearHistoryDialogVisible.value = false }) {
                            Text(text = "Cancel")
                        }
                    },
                )
            }

        }

    }

}


@Composable
fun YearlyMarksHistoryItem(yearlyMarks: YearlyMarks,onDeleted:()->Unit) {
    ElevatedCard(modifier = Modifier.padding(5.dp),) {
        Column {
            Row {
                if (yearlyMarks.oddSemGpa>0.0 &&yearlyMarks.oddSemSubjects>0){
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        YearlyMarksCardItem(
                            title = "Odd Sem",
                            subject = yearlyMarks.oddSemSubjects,
                            cgpa = yearlyMarks.oddSemGpa,
                            obtainedNumber = yearlyMarks.oddSemObtainedMarks,
                            totalNumber = yearlyMarks.oddSemTotalMarks,
                            percentage = yearlyMarks.oddSemPercentage
                        )
                    }
                }

                if (yearlyMarks.evenSemGpa>0.0 && yearlyMarks.evenSemSubjects>0){
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        YearlyMarksCardItem(
                            title = "Even Sem",
                            subject = yearlyMarks.evenSemSubjects,
                            cgpa = yearlyMarks.evenSemGpa,
                            obtainedNumber = yearlyMarks.evenSemObtainedMarks,
                            totalNumber = yearlyMarks.evenSemTotalMarks,
                            percentage = yearlyMarks.evenSemPercentage
                        )
                    }
                }
            }
            if (yearlyMarks.totalMarks>0.0 &&yearlyMarks.totalPercentage>0){
                Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.Center) {
                    YearlyMarksCardItem(
                        title = "Total Marks",
                        obtainedNumber = yearlyMarks.totalObtainedMarks,
                        totalNumber = yearlyMarks.totalMarks,
                        percentage = yearlyMarks.totalPercentage
                    )
                }
            }
            Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.Center) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    TextButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onDeleted.invoke()
                        }) {
                        Icon(imageVector = Icons.Outlined.Delete, contentDescription = "")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "Delete")
                    }
//                    Spacer(modifier = Modifier.width(10.dp))
//                    TextButton(
//                        modifier = Modifier.weight(1f),
//                        onClick = { /*TODO*/ })
//                    {
//                    Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "")
//                    Spacer(modifier = Modifier.width(5.dp))
//                    Text(text = "Mark Favourite")
//                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
                        Text(text = SimpleDateFormat(Constant.DATE_FORMAT, Locale.getDefault()).format(yearlyMarks.timeStamp), style = MaterialTheme.typography.titleSmall)
                    }

                }
            }

        }

    }
}

@Composable
fun YearlyMarksCardItem(
    title: String,
    subject:Int = 0,
    cgpa: Double = 0.0,
    obtainedNumber: Double,
    totalNumber: Int,
    percentage: Double
) {
    OutlinedCard {
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

            if (subject>0 && cgpa>0.0){
                Row {
                    Text(text = "Subjects :", style = MaterialTheme.typography.titleSmall)
                    SelectionContainer {
                        Text(text = "$subject",)
                    }
                }

                Row {
                    Text(text = "CGPA :", style = MaterialTheme.typography.titleSmall)
                    SelectionContainer {
                        Text(text = "$cgpa",)
                    }
                }
            }

            Row {
                Text(text = "Percentage :", style = MaterialTheme.typography.titleSmall)
                SelectionContainer {
                    Text(text = "$percentage%",)
                }
            }
            Row {
                Text(text = "Mark :", style = MaterialTheme.typography.titleSmall)
                SelectionContainer {
                    Text(text = "$obtainedNumber out of $totalNumber")
                }
            }
        }
    }
}