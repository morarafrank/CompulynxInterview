package com.morarafrank.compulynxinterview.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.morarafrank.compulynxinterview.ui.theme.regularFont

@Composable
fun NoDataUi(
    error: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


//        val composition = rememberLottieComposition(
//            spec = LottieCompositionSpec(R.raw.no_data_anim),
//        )
//        LottieAnimation(
//            composition = composition,
//            modifier = Modifier
//                .size(200.dp),
//            iterations = Int.MAX_VALUE,
//        )



        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = error,
            textAlign = TextAlign.Center,
            maxLines = 2,
            fontFamily = regularFont,
        )
    }
}