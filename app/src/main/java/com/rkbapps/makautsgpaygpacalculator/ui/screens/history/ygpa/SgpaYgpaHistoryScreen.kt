package com.rkbapps.makautsgpaygpacalculator.ui.screens.history.ygpa

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rkbapps.makautsgpaygpacalculator.db.entity.GpaPercentage
import com.rkbapps.makautsgpaygpacalculator.ui.screens.home.AppTopBar
import com.rkbapps.makautsgpaygpacalculator.utils.Constant
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun SgpaYgpaHistoryScreen(
    backStack: SnapshotStateList<Any>
) {
    val viewModel: SgpaYgpaHistoryViewModel = hiltViewModel()
    val sgpaYgpaHistory = viewModel.sgpaYgpaHistory.collectAsState()

    val isDescOrdered = rememberSaveable {
        mutableStateOf(true)
    }

    val isClearHistoryDialogVisible = rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = isDescOrdered.value) {
        if (isDescOrdered.value) {
            viewModel.getAllByTimeDesc()
        } else {
            viewModel.getAllByTimeAsc()
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(showBack = true) {
                backStack.removeLastOrNull()
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "History", style = MaterialTheme.typography.titleLarge)
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        isDescOrdered.value = !isDescOrdered.value
                    }) {
                        Icon(
                            imageVector = if (isDescOrdered.value) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                            contentDescription = "ace and dec"
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    IconButton(onClick = {
                        isClearHistoryDialogVisible.value = true
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "clear history"
                        )
                    }
                }
            }

            if (sgpaYgpaHistory.value.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    items(count = sgpaYgpaHistory.value.size, key = {
                        sgpaYgpaHistory.value[it].id
                    }) {

                        SgpaYgpaHistoryItem(gpaPercentage = sgpaYgpaHistory.value[it]) {
                            viewModel.delete(sgpaYgpaHistory.value[it])
                        }

                    }
                }

            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "You have no history yet.")
                }
            }

            if (isClearHistoryDialogVisible.value) {
                AlertDialog(
                    onDismissRequest = { isClearHistoryDialogVisible.value = false },
                    title = { Text(text = "Clear History") },
                    text = { Text(text = "Are you sure you want to clear history?") },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.deleteAll()
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
fun SgpaYgpaHistoryItem(
    gpaPercentage: GpaPercentage,
    onDeleted: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.padding(vertical = 5.dp),
    ) {
        Column {
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "CGPA/YGPA :", style = MaterialTheme.typography.titleSmall)
                        SelectionContainer {
                            Text(text = "${gpaPercentage.gpa}")
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Percentage :", style = MaterialTheme.typography.titleSmall)
                        SelectionContainer {
                            Text(text = "${gpaPercentage.percentage}")
                        }
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
                    Spacer(modifier = Modifier.width(10.dp))

                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        Text(
                            text = SimpleDateFormat(
                                Constant.DATE_FORMAT,
                                Locale.getDefault()
                            ).format(gpaPercentage.timeStamps),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                }
            }
        }
    }

}