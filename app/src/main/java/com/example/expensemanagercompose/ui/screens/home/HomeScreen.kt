package com.example.expensemanagercompose.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.expensemanagercompose.R
import com.example.expensemanagercompose.ui.theme.*
import com.example.expensemanagercompose.ui.util.Constants
import kotlin.math.exp

@Composable
fun HomeScreen(
    navController: NavController,
    vm: HomeViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        WelcomeCard(userName = "Rahul")

        Spacer(modifier = Modifier.height(16.dp))

        val durationSelectors = listOf(
            DurationSelector(
                title = "Daily"
            ),
            DurationSelector(
                title = "Weekly"
            ),
            DurationSelector(
                title = "Monthly"
            )
        )

        val selectedDuration by remember {
            vm.selectedDuration
        }

        durationSelectors.forEach {
            it.selected = it.title == selectedDuration
        }

        DurationSelector(
            durationSelectors = durationSelectors,
            vm = vm
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Expenses",
                style = TextStyle(
                    color = Black
                ),
                fontFamily = appFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )

            Image(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Add",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        navController
                            .navigate(Constants.ADD_EXPENSES_SCREEN)
                    }
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        val expensesItems = listOf(
            ExpensesItem(
                title = "Shopping",
                percentage = 28f,
                fromDate = "30 July",
                amount = 600f,
                backgroundColor = LightPurple,
                variantColor = LightPurpleLightVariant,
                highlightColor = White
            ),
            ExpensesItem(
                title = "Transport",
                percentage = 60f,
                fromDate = "30 July",
                amount = 450f,
                backgroundColor = LightPink,
                variantColor = LightPinkLightVariant,
                highlightColor = Black
            ),
            ExpensesItem(
                title = "Food",
                percentage = 12f,
                fromDate = "30 July",
                amount = 600f,
                backgroundColor = LightPurple,
                variantColor = LightPurpleLightVariant,
                highlightColor = White
            ),
            ExpensesItem(
                title = "Food",
                percentage = 12f,
                fromDate = "30 July",
                amount = 600f,
                backgroundColor = LightPurple,
                variantColor = LightPurpleLightVariant,
                highlightColor = White
            ),
            ExpensesItem(
                title = "Food",
                percentage = 12f,
                fromDate = "30 July",
                amount = 600f,
                backgroundColor = LightPurple,
                variantColor = LightPurpleLightVariant,
                highlightColor = White
            ),
            ExpensesItem(
                title = "Food",
                percentage = 12f,
                fromDate = "30 July",
                amount = 600f,
                backgroundColor = LightPurple,
                variantColor = LightPurpleLightVariant,
                highlightColor = White
            ),
            ExpensesItem(
                title = "Food",
                percentage = 12f,
                fromDate = "30 July",
                amount = 600f,
                backgroundColor = LightPurple,
                variantColor = LightPurpleLightVariant,
                highlightColor = White
            ),
            ExpensesItem(
                title = "Food",
                percentage = 12f,
                fromDate = "30 July",
                amount = 600f,
                backgroundColor = LightPurple,
                variantColor = LightPurpleLightVariant,
                highlightColor = White
            ),
            ExpensesItem(
                title = "Food",
                percentage = 12f,
                fromDate = "30 July",
                amount = 600f,
                backgroundColor = LightPurple,
                variantColor = LightPurpleLightVariant,
                highlightColor = White
            ),
            ExpensesItem(
                title = "Food",
                percentage = 12f,
                fromDate = "30 July",
                amount = 600f,
                backgroundColor = LightPurple,
                variantColor = LightPurpleLightVariant,
                highlightColor = White
            ),
            ExpensesItem(
                title = "Food",
                percentage = 12f,
                fromDate = "30 July",
                amount = 600f,
                backgroundColor = LightPurple,
                variantColor = LightPurpleLightVariant,
                highlightColor = White
            )
        )


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            items(
                count = expensesItems.size
            ){index->
                val backgroundColor = if (index % 2 == 0) LightPurpleLightVariant else LightPinkLightVariant
                val textColor = if (index % 2 == 0) White else Black

                ExpensesItem(
                    backgroundColor = backgroundColor,
                    textColor = textColor,
                    expensesItem = expensesItems[index]
                ){

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

@Composable
fun DurationSelector(
    durationSelectors:List<DurationSelector>,
    vm: HomeViewModel
){
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
                    vm.setSelectedDuration(duration = selectedItem.title)
                }
            }
        }
    }
}

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
                    text = "Hi $userName",
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
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