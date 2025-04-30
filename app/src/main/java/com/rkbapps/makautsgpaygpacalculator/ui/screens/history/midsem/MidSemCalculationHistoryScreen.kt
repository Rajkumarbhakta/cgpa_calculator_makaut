package com.rkbapps.makautsgpaygpacalculator.ui.screens.history.midsem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rkbapps.makautsgpaygpacalculator.db.entity.DgpaMidSemMarks
import com.rkbapps.makautsgpaygpacalculator.ui.screens.home.AppTopBar
import com.rkbapps.makautsgpaygpacalculator.ui.screens.midsem.Options
import com.rkbapps.makautsgpaygpacalculator.utils.Constant
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MidSemCalculationHistoryScreen(navController: NavHostController) {
    val viewModel:MidSemCalculationHistoryViewModel = hiltViewModel()
    val midSemHistory = viewModel.midSemHistory.collectAsState()

    val isDescOrdered = rememberSaveable {
        mutableStateOf(true)
    }

    val isClearHistoryDialogVisible = rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = isDescOrdered.value) {
        if (isDescOrdered.value){
            viewModel.getHistoryInDesc()
        }else{
            viewModel.getHistoryInAsc()
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(showBack = true){
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
                        Icon(imageVector = if (isDescOrdered.value) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp, contentDescription = "ace and dec")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    IconButton(onClick = {
                        isClearHistoryDialogVisible.value = true
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "clear history")
                    }
                }
            }

            if (midSemHistory.value.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp,)
                ) {
                    items(count = midSemHistory.value.size, key = {
                        midSemHistory.value[it].id
                    }){

                        MidSemHistoryItem(item = midSemHistory.value[it]) {
                            viewModel.delete(midSemHistory.value[it])
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
                            viewModel.deleteHistory()
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
fun MidSemHistoryItem(item:DgpaMidSemMarks,onDeleted:()->Unit) {
    ElevatedCard(modifier = Modifier.padding(vertical = 5.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            OutlinedCard {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    if (item.firstSemGpa > 0.0) MidSemHistoryItemRowItem(
                        title = "1st Semester",
                        value = 5.6
                    )
                    if (item.secondSemGpa > 0.0) MidSemHistoryItemRowItem(
                        title = "2nd Semester",
                        value = 5.5
                    )
                    if (item.thirdSemGpa > 0.0) MidSemHistoryItemRowItem(
                        title = Options.THIRD_SEM,
                        value = 6.5
                    )
                    if (item.fourthSemGpa > 0.0) MidSemHistoryItemRowItem(
                        title = Options.FOURTH_SEM,
                        value = 8.5
                    )
                    if (item.fifthSemGpa > 0.0) MidSemHistoryItemRowItem(
                        title = Options.FIFTH_SEM,
                        value = 9.8
                    )
                    if (item.sixthSemGpa > 0.0) MidSemHistoryItemRowItem(
                        title = Options.SIXTH_SEM,
                        value = 7.8
                    )
                    if (item.seventhSemGpa > 0.0) MidSemHistoryItemRowItem(
                        title = Options.SEVENTH_SEM,
                        value = 9.9
                    )
                }
            }
            if (item.avgGpa > 0.0 && item.avgPercentage > 0.0) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedCard {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        MidSemHistoryItemRowItem(title = "Average CGPA", value = 5.6)
                        MidSemHistoryItemRowItem(title = "Average Percentage", value = 5.5)
                    }
                }
            }
            Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.Center) {
                Row(
                    Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
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

                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        Text(
                            text = SimpleDateFormat(
                                Constant.DATE_FORMAT,
                                Locale.getDefault()
                            ).format(item.timestamp), style = MaterialTheme.typography.titleSmall
                        )
                    }

                }
            }

        }
    }

}

@Composable
fun MidSemHistoryItemRowItem(
    modifier: Modifier = Modifier,
    title: String,
    value: Double
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(text = "$title :", style = MaterialTheme.typography.titleSmall)
        SelectionContainer {
            Text(text = "${value}",)
        }
    }
}