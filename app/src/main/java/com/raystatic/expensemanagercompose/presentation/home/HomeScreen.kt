package com.raystatic.expensemanagercompose.presentation.home

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.raystatic.expensemanagercompose.R
import com.raystatic.expensemanagercompose.presentation.Screen
import com.raystatic.expensemanagercompose.presentation.home.components.DurationSelector
import com.raystatic.expensemanagercompose.presentation.home.components.WelcomeCard
import com.raystatic.expensemanagercompose.presentation.ui.theme.*
import com.raystatic.expensemanagercompose.util.Constants

@Composable
fun HomeScreen(
    navController: NavController,
    vm: HomeViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val user = vm.user.observeAsState().value

        val selectedDuration by remember {
            vm.selectedDuration
        }

        val durations = Constants.defaultDurationList
        durations.forEach {
            it.selected = it.title == selectedDuration
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding =  PaddingValues(16.dp)
        ){
            item {
                WelcomeCard(userName = user?.name ?: "")

            }

            item {

                Spacer(modifier = Modifier.height(10.dp))

                DurationSelector(durationSelectors = durations){
                    vm.setSelectedDuration(it)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Expenses",
                        style = TextStyle(
                            color = Black
                        ),
                        fontFamily = appFontFamily,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )

                    Image(
                        painter = painterResource(id =R.drawable.ic_add),
                        contentDescription = "add expenses",
                        modifier = Modifier.padding(10.dp)
                            .clickable {
                                navController.navigate(Screen.AddExpensesScreen.route)
                            }
                    )

                }
            }

        }
    }

}

@Composable
fun ExpensesItem(
    backgroundColor: Color,
    textColor:Color,
    expensesItem: ExpensesItem,
    onClick:(ExpensesItem) -> Unit
){

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = backgroundColor)
            .fillMaxWidth()
            .clickable {
                onClick(expensesItem)
            }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = expensesItem.title,
                    style = TextStyle(
                        color = textColor
                    ),
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Text(
                    text = expensesItem.fromDate,
                    style = TextStyle(
                        color = textColor
                    ),
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            }

            Text(
                text = "₹${expensesItem.amount}",
                style = TextStyle(
                    color = textColor
                ),
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

        }
    }


//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(16.dp))
//            .background(color = expensesItem.backgroundColor)
//    ) {
//
//        Column(
//            modifier = Modifier
//                .padding(top = 20.dp, bottom = 20.dp, start = 30.dp, end = 30.dp)
//        ) {
//            Row {
//                Column {
//                    Text(
//                        text = "${expensesItem.percentage}%",
//                        style = TextStyle(
//                            color = expensesItem.highlightColor
//                        ),
//                        fontFamily = appFontFamily,
//                        fontWeight = FontWeight.Medium,
//                        fontSize = 14.sp
//                    )
//
//                    Text(
//                        text = expensesItem.title,
//                        style = TextStyle(
//                            color = expensesItem.highlightColor
//                        ),
//                        fontFamily = appFontFamily,
//                        fontWeight = FontWeight.Medium,
//                        fontSize = 12.sp
//                    )
//                }
//
//                Spacer(modifier = Modifier.width(30.dp))
//
//                CircularProgressBar(
//                    percentage = expensesItem.percentage / 100,
//                    variantColor = expensesItem.variantColor,
//                    color = expensesItem.highlightColor
//                )
//            }
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Text(
//                text = "₹ ${expensesItem.amount}",
//                style = TextStyle(
//                    color = expensesItem.highlightColor
//                ),
//                fontSize = 18.sp,
//                fontFamily = appFontFamily,
//                fontWeight = FontWeight.Medium
//            )
//
//            Text(
//                text = "Since ${expensesItem.fromDate}",
//                style = TextStyle(
//                    color = expensesItem.highlightColor
//                ),
//                fontSize = 12.sp,
//                fontFamily = appFontFamily,
//                fontWeight = FontWeight.Medium
//            )
//
//        }
//    }

}

@Composable
fun CircularProgressBar(
    percentage:Float,
    variantColor: Color,
    color: Color
){

    val radius = 25.dp
    val strokeWidth = 4.dp

    Box(
        modifier = Modifier.size(radius * 2f),
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier
                .size(radius * 2f)
        ){
            drawArc(
                color = variantColor,
                startAngle = -90f,
                sweepAngle =  360 * 1f,
                useCenter = false,
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )

            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle =  360 * percentage,
                useCenter = false,
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )

        }
        Text(
            text = "28%",
            style = TextStyle(
                color = color
            ),
            fontFamily = appFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }

}


