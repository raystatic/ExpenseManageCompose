package com.raystatic.expensemanagercompose.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.raystatic.expensemanagercompose.R
import com.raystatic.expensemanagercompose.presentation.ui.theme.LightBlue
import com.raystatic.expensemanagercompose.presentation.ui.theme.appFontFamily

@Composable
fun WelcomeCard(
    userName:String
){

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = LightBlue)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.Bottom)
                    .padding(20.dp)
            ) {
                Text(
                    text = "Hey there,",
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "Manage your expenses for today",
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

            }

            Image(
                painter = painterResource(id = R.drawable.ic_wallet),
                contentDescription = "Wallet",
                modifier = Modifier
                    .size(200.dp)
                    .weight(1f),
            )
        }
    }

}