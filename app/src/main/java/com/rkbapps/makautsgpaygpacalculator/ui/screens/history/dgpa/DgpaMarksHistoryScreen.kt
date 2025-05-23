package com.rkbapps.makautsgpaygpacalculator.ui.screens.history.dgpa

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.rkbapps.makautsgpaygpacalculator.ui.screens.history.midsem.MidSemHistoryItem
import com.rkbapps.makautsgpaygpacalculator.ui.screens.home.AppTopBar

@Composable
fun DgpaMarksHistoryScreen(
    backStack: SnapshotStateList<Any>
) {
    val viewModel: DgpaMarksHistoryViewModel = hiltViewModel()
    val dgpaHistory = viewModel.dgpaHistory.collectAsState()

    val isDescOrdered = rememberSaveable {
        mutableStateOf(true)
    }

    val isClearHistoryDialogVisible = rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = isDescOrdered.value) {
        if (isDescOrdered.value) {
            viewModel.getHistoryInDesc()
        } else {
            viewModel.getHistoryInAsc()
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

            if (dgpaHistory.value.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    items(count = dgpaHistory.value.size, key = {
                        dgpaHistory.value[it].id
                    }) {

                        MidSemHistoryItem(item = dgpaHistory.value[it]) {
                            viewModel.delete(dgpaHistory.value[it])
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