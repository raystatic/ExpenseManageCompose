package com.raystatic.expensemanagercompose.presentation.add_expenses

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.raystatic.expensemanagercompose.R
import com.raystatic.expensemanagercompose.data.remote.dto.AddExpenseRequest
import com.raystatic.expensemanagercompose.data.remote.dto.UpdateExpenseRequest
import com.raystatic.expensemanagercompose.presentation.common.Loader
import com.raystatic.expensemanagercompose.presentation.ui.theme.*
import com.raystatic.expensemanagercompose.util.Constants
import com.raystatic.expensemanagercompose.util.PrefManager
import com.raystatic.expensemanagercompose.util.Utility
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

@Composable
fun AddExpense(
    navController: NavController,
    prefManager: PrefManager,
    vm:AddExpenseViewModel = hiltViewModel()
){

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        val scope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            scaffoldState = scaffoldState,
        ) {

            val expenseByIDFromCache = vm.expenseByIdFromCache.observeAsState().value?.getContentIfNotHandled()

            val updateExpenseState = vm.updateExpenseState.observeAsState().value?.getContentIfNotHandled()

            if (updateExpenseState?.isLoading == true){
                Loader(
                    modifier = Modifier.align(Alignment.Center)
                ) {

                }
            }

            if (updateExpenseState?.updated == true){
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Updated successfully!",
                    )
                }
            }

            if (updateExpenseState?.error?.isNotBlank() == true){
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = updateExpenseState.error,
                    )
                }
            }

            val addExpenseState = vm.addExpenseState.observeAsState().value?.getContentIfNotHandled()

            if (addExpenseState?.error?.isNotBlank() == true){
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = addExpenseState.error,
                    )
                }
            }

            if (addExpenseState?.addedExpense!=null){
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "New expense added",
                    )
                }
                navController.navigateUp()
            }

            if (addExpenseState?.isLoading == true){
                Loader(
                    modifier = Modifier.align(Alignment.Center)
                ) {

                }
            }

            val defaultTitle =  ""
            val defaultAmount = 0f


            val expenseTitle = remember {
                mutableStateOf(defaultTitle)
            }

            val expenseAmount = remember {
                mutableStateOf(defaultAmount)
            }

            val updatable = remember {
                mutableStateOf(false)
            }

            val expenseId = remember {
                mutableStateOf(-1)
            }

            if (expenseByIDFromCache!=null){
                expenseTitle.value = expenseByIDFromCache.title
                expenseAmount.value = expenseByIDFromCache.amount
                expenseId.value = expenseByIDFromCache.id
                updatable.value = true
            }


            Column(
                modifier = Modifier
                    .padding(20.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    BackButton {
                        navController.navigateUp()
                    }

                    Text(
                        text = "Delete",
                        style = TextStyle(
                            color = Black
                        ),
                        fontFamily = appFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )

                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Title",
                    style = TextStyle(
                        color = Black
                    ),
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                InputField(
                    hint = "Expense Name",
                    textValue = expenseTitle.value
                ){
                    expenseTitle.value = it
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Amount",
                    style = TextStyle(
                        color = Black
                    ),
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                InputField(
                    hint = "Expense Amount",
                    isNumerical = true,
                    textValue = if (expenseAmount.value == 0f) "" else expenseAmount.value.toString()
                ){
                    if (it.isNotBlank()){
                        try {
                            expenseAmount.value = it.toFloat()
                        }catch (e:Exception){
                            e.printStackTrace()
                        }
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Date",
                    style = TextStyle(
                        color = Black
                    ),
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                var selectedDateInIso by remember {
                    mutableStateOf(Utility.getDateInIso(LocalDate.now()))
                }

                var selectedDate by remember {
                    mutableStateOf(Utility.formatDate(LocalDate.now().toString()))

                }

                if (expenseByIDFromCache != null) {
                    expenseByIDFromCache.date?.let {
                        selectedDateInIso = it
                        selectedDate = Utility.formatDate(it)
                    } ?: kotlin.run {
                        selectedDateInIso = expenseByIDFromCache.updatedAt
                        selectedDate = Utility.formatDate(expenseByIDFromCache.updatedAt)
                    }
                }


                val dialog = remember {
                    MaterialDialog()
                }

                dialog.build(buttons = {
                    positiveButton("SET")
                    negativeButton("Cancel")
                },
                    backgroundColor = White,

                    ) {
                    datepicker{date->
                        selectedDate = Utility.formatDate(date.toString())
                        selectedDateInIso = Utility.getDateInIso(date)
                    }
                }


                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = { },
                    label = { Text("Tap to choose date") },
                    textStyle = TextStyle(
                        color = Black,
                        fontFamily = appFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                    ),
                    enabled = false,
                    modifier = Modifier
                        .focusable(false)
                        .clickable {
                            dialog.show()
                        },
                )


                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    RoundedButton(
                        text = "Submit",
                    ) {
                        if (expenseTitle.value.isEmpty()){
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Title cannot be empty")
                            }
                            return@RoundedButton
                        }

                        if (expenseAmount.value == 0f){
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Please enter an amount")
                            }
                            return@RoundedButton
                        }

                        val token = prefManager.getString(Constants.USERTOKENKEY) ?: ""
                        if (token.isNotBlank()){

                            if (updatable.value && expenseId.value != -1){
                                val updateExpenseRequest = UpdateExpenseRequest(
                                    title = expenseTitle.value,
                                    amount = expenseAmount.value,
                                    expenseId = expenseId.value,
                                    date = selectedDateInIso
                                )

                                vm.updateExpense(token = token, updateExpenseRequest = updateExpenseRequest)
                            }else{
                                val addExpenseRequest = AddExpenseRequest(
                                    title = expenseTitle.value,
                                    amount = expenseAmount.value,
                                    date = selectedDateInIso
                                )
                                vm.addExpense(addExpenseRequest = addExpenseRequest, token = token)
                            }
                        }
                     }

                    Spacer(modifier = Modifier.height(10.dp))
                }

            }
        }


    }


}


@Composable
fun RoundedButton(
    text:String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
){

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 10.dp,
        modifier = Modifier.clickable {
            onClick()
        }
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = LightPurple)
                .padding(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = text,
                style = TextStyle(
                    color = White
                ),
                fontFamily = appFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )

        }

    }

}

@Composable
fun InputField(
    hint:String = "",
    isNumerical:Boolean = false,
    textValue:String = "",
    onValueChanged:(String) -> Unit
){

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(
         modifier = Modifier.fillMaxWidth()
    ){
        BasicTextField(
            value = textValue,
            onValueChange = {
                onValueChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .onFocusEvent {
                    isHintDisplayed = !it.isFocused && textValue.isEmpty()
                },
            textStyle = TextStyle(
                color = Black,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = if (isNumerical) KeyboardType.Number else KeyboardType.Text)
        )

        if (isHintDisplayed){
            Text(
                text = hint,
                style = TextStyle(
                    color = Grey
                ),
                fontSize = 20.sp,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

    }

}

@Composable
fun BackButton(
    onClick:() -> Unit
){
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color = Grey)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24) ,
                contentDescription = "Back",
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.CenterVertically)
            )

            Text(
                text = "Back",
                style = TextStyle(
                    color = Black
                ),
                fontFamily = appFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

        }
    }
}