package com.raystatic.expensemanagercompose.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.loader.content.Loader
import com.raystatic.expensemanagercompose.presentation.ui.theme.LightPurple
import com.raystatic.expensemanagercompose.presentation.ui.theme.White

@Composable
fun Loader(
    modifier: Modifier = Modifier,
    onDismiss:() -> Unit
) {
    Dialog(
        onDismissRequest = {  },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment= Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .background(White, shape = RoundedCornerShape(8.dp))
        ) {
            CircularProgressIndicator(
                color = LightPurple
            )
        }
    }
}