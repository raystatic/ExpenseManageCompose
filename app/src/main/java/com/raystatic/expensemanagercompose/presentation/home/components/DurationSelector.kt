package com.raystatic.expensemanagercompose.presentation.home.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.raystatic.expensemanagercompose.presentation.home.DurationSelector
import com.raystatic.expensemanagercompose.presentation.ui.theme.Black

@Composable
fun DurationSelector(
    durationSelectors:List<DurationSelector>,
    onSelectDuration:(DurationSelector) -> Unit
){
    Log.d("TAGDEBUG", "HomeScreen item: $durationSelectors")
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .background(color = Black)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ){
            durationSelectors.forEach {
                DurationSelectorItem(
                    durationSelector= it,
                    modifier = Modifier
                        .weight(1f)
                ){selectedItem ->
                   onSelectDuration(selectedItem)
                }
            }
        }
    }
}