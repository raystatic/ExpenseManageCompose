package com.raystatic.expensemanagercompose.presentation.home

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.raystatic.expensemanagercompose.R
import com.raystatic.expensemanagercompose.domain.models.Expense
import com.raystatic.expensemanagercompose.domain.models.MonthlyExpenseItem
import com.raystatic.expensemanagercompose.presentation.Screen
import com.raystatic.expensemanagercompose.presentation.home.components.DurationSelector
import com.raystatic.expensemanagercompose.presentation.home.components.WelcomeCard
import com.raystatic.expensemanagercompose.presentation.ui.theme.*
import com.raystatic.expensemanagercompose.util.Constants
import com.raystatic.expensemanagercompose.util.Utility
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavController,
    vm: HomeViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
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

            val expensesFromLocal = vm.getExpensesFromCache().collectAsState(initial = emptyList()).value

            val expenseListState = vm.expenseListState.observeAsState().value?.getContentIfNotHandled()

            val monthlyExpenses = vm.monthlyExpensesState.observeAsState().value?.peekContent()

            if (expenseListState?.error?.isNotBlank() == true){
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = expenseListState.error
                    )
                }
            }

            Column{

                WelcomeCard(
                    userName = user?.name ?: "",
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))
                DurationSelector(
                    durationSelectors = durations,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ){
                    vm.setSelectedDuration(it)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
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

                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                navController.navigate(Screen.AddExpensesScreen.route + "/-1")
                            }
                    ) {

                        Text(
                            text = "Add New",
                            style = TextStyle(
                                color = Black
                            ),
                            fontFamily = appFontFamily,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                        
                        Spacer(modifier = Modifier.width(5.dp))

                        Image(
                            painter = painterResource(id =R.drawable.ic_add),
                            contentDescription = "add expenses",
                            modifier = Modifier
                                .size(16.dp)
                                .align(Alignment.CenterVertically)
                        )

                    }

                }


                when(selectedDuration){
                    "Daily" -> {
                        if (expensesFromLocal.isNotEmpty()){
                            LazyColumn(
                                contentPadding = PaddingValues(16.dp)
                            ){
                                itemsIndexed(expensesFromLocal){index: Int, item: Expense ->
                                    val backgroundColor: Color
                                    val highlightColor: Color
                                    if (index%2 != 0){
                                        backgroundColor = LightPurple
                                        highlightColor = White
                                    }else{
                                        backgroundColor = LightPink
                                        highlightColor = Black
                                    }

                                    ExpensesItem(
                                        backgroundColor = backgroundColor,
                                        textColor = highlightColor,
                                        expense = item,
                                        onClick ={
                                            navController.navigate(Screen.AddExpensesScreen.route + "/${it.id}")
                                        },
                                        onMonthlyClick = {

                                        }
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                }
                            }
                        }else{
                            Box(modifier = Modifier.fillMaxSize()){
                                Text(
                                    text = "No expenses yet.",
                                    style = TextStyle(
                                        color = Black
                                    ),
                                    fontSize =  16.sp,
                                    fontFamily = appFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }

                    "Monthly" -> {

                        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
                            bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
                        )

                        BottomSheetScaffold(
                            scaffoldState = bottomSheetScaffoldState,
                            sheetContent ={
                                val expensesByMonth = vm.expensesOfMonth.observeAsState().value
                                BottomSheetContent(
                                    expenses = expensesByMonth?.monthWiseExpense,
                                    backgroundColor = expensesByMonth?.backgroundColor,
                                    textColor = expensesByMonth?.textColor,
                                    variantColor = expensesByMonth?.variantColor
                                )

                            },
                            sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                            sheetElevation = 10.dp,
                            sheetPeekHeight = 0.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            monthlyExpenses?.monthlyExpense?.let { monthly->
                                if (monthly.isNotEmpty()){
                                    LazyColumn(
                                        contentPadding = PaddingValues(16.dp)
                                    ){
                                        itemsIndexed(monthly){index: Int, item: MonthlyExpenseItem ->
                                            val backgroundColor: Color
                                            val highlightColor: Color
                                            if (index%2 != 0){
                                                backgroundColor = LightPurple
                                                highlightColor = White
                                            }else{
                                                backgroundColor = LightPink
                                                highlightColor = Black
                                            }

                                            ExpensesItem(
                                                backgroundColor = backgroundColor,
                                                textColor = highlightColor,
                                                monthlyExpenseItem = item,
                                                onClick ={

                                                },
                                                onMonthlyClick = {
                                                    vm.getExpensesByMonth(it.month, index = index)
                                                    scope.launch {
                                                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed){
                                                            bottomSheetScaffoldState.bottomSheetState.expand()
                                                        }else{
                                                            bottomSheetScaffoldState.bottomSheetState.collapse()
                                                        }
                                                    }
                                                }
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                        }
                                    }
                                }else{
                                    Box(modifier = Modifier.fillMaxSize()){
                                        Text(
                                            text = "No expenses yet.",
                                            style = TextStyle(
                                                color = Black
                                            ),
                                            fontSize =  16.sp,
                                            fontFamily = appFontFamily,
                                            fontWeight = FontWeight.Medium,
                                            modifier = Modifier.align(Alignment.Center)
                                        )
                                    }
                                }
                            }
                        }

                    }
                }

            }

        }
    }



}


@Composable
fun BottomSheetContent(
    expenses:List<Expense>?=null,
    backgroundColor: Color? ,
    textColor: Color? ,
    variantColor: Color?
){

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = backgroundColor ?: LightPurple)
        .padding(10.dp)
    ){
        expenses?.let {list->
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                itemsIndexed(list){index:Int,item: Expense ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column{
                            Text(
                                text = item.title,
                                style = TextStyle(
                                    color = textColor ?: White
                                ),
                                fontFamily = appFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )

                            Text(
                                text = Utility.formatDate(item.date ?: item.updatedAt),
                                style = TextStyle(
                                    color = textColor ?: White
                                ),
                                fontFamily = appFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp
                            )
                        }

                        Text(
                            text = "₹${item.amount}",
                            style = TextStyle(
                                color = textColor ?: White
                            ),
                            fontFamily = appFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )

                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if (index < list.size - 1){
                        Divider(color = variantColor ?: LightPurpleLightVariant,thickness = 1.dp)
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                }
            }
        }
    }


}

@Composable
fun ExpensesItem(
    backgroundColor: Color,
    textColor:Color,
    expense: Expense? =null,
    monthlyExpenseItem: MonthlyExpenseItem ?= null,
    onClick:(Expense) -> Unit,
    onMonthlyClick:(MonthlyExpenseItem) -> Unit
){

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = backgroundColor)
            .fillMaxWidth()
            .clickable {
                if (expense != null) {
                    onClick(expense)
                } else if (monthlyExpenseItem != null) {
                    onMonthlyClick(monthlyExpenseItem)
                }
            }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                val title = expense?.title ?: monthlyExpenseItem?.month  ?: ""
                Text(
                    text = title,
                    style = TextStyle(
                        color = textColor
                    ),
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                val date = if (expense!=null){
                    expense.date?.let { Utility.formatDate(it) } ?: Utility.formatDate(expense.updatedAt)
                }else{
                    monthlyExpenseItem?.duration ?: ""
                }
                Text(
                    text = date,
                    style = TextStyle(
                        color = textColor
                    ),
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            }

            val amount = expense?.amount ?: monthlyExpenseItem?.amount

            Text(
                text = "₹${amount}",
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


