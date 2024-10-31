package com.morarafrank.compulynxinterview.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun SingleTransactionRow(
    modifier: Modifier = Modifier,
    transactionId: String,
    amount: String,
    fontFamily: FontFamily
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
//            .padding(4.dp)
            .border(0.5.dp, Color.Black)
            .padding(4.dp)
        ,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){

        Text(text = transactionId, fontFamily = fontFamily)
        Text(text = "|", modifier = modifier.fillMaxHeight())
        Text(text = amount, fontFamily = fontFamily)

    }
}