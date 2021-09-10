package com.raystatic.expensemanagercompose.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raystatic.expensemanagercompose.presentation.home.DurationSelector
import com.raystatic.expensemanagercompose.presentation.ui.theme.Black
import com.raystatic.expensemanagercompose.presentation.ui.theme.White
import com.raystatic.expensemanagercompose.presentation.ui.theme.appFontFamily

@Composable
fun DurationSelectorItem(
    durationSelector: DurationSelector,
    modifier: Modifier = Modifier,
    onClick:(DurationSelector) -> Unit
){

    val textColor = if (durationSelector.selected) Black else White
    val backgroundColor = if (durationSelector.selected) White else Black

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = backgroundColor)
            .clickable {
                onClick(durationSelector)
            },
    ) {
        Text(
            text = durationSelector.title,
            fontFamily = appFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            style = TextStyle(
                color = textColor,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        )
    }


}