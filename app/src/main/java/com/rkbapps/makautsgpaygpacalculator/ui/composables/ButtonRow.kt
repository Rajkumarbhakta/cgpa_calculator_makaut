package com.rkbapps.makautsgpaygpacalculator.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonRow(
    modifier: Modifier = Modifier,
    onReset:()-> Unit,
    onCalculate:()-> Unit
){
    Row(modifier = modifier.fillMaxWidth()) {

        OutlinedButton(onClick = onReset, modifier = Modifier.weight(1f)) {
            Text(text = "Reset")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = onCalculate, modifier = Modifier.weight(1f)) {
            Text(text = "Calculate")
        }
    }
}