package com.raystatic.expensemanagercompose.presentation.add_expenses

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
import androidx.navigation.NavController
import com.raystatic.expensemanagercompose.R
import com.raystatic.expensemanagercompose.presentation.ui.theme.*
import com.raystatic.expensemanagercompose.util.Utility
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.LocalDate

@Composable
fun AddExpense(
    navController: NavController
){
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
                text = "New Expense",
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

        Spacer(modifier = Modifier.height(20.dp))

        InputField(
            hint = "Expense Name"
        )

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
            isNumerical = true
        )

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

        var selectedDate by remember {
            val localDate = LocalDate.now()
            mutableStateOf(Utility.formatDate(localDate.toString()))
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
                fontSize = 24.sp,
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

            }

            Spacer(modifier = Modifier.height(10.dp))
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
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )

        }

    }

}

@Composable
fun InputField(
    hint:String = "",
    isNumerical:Boolean = false
){
    val textValue = remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(
         modifier = Modifier.fillMaxWidth()
    ){
        BasicTextField(
            value = textValue.value,
            onValueChange = {
                textValue.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .onFocusEvent {
                    isHintDisplayed = !it.isFocused && textValue.value.isEmpty()
                },
            textStyle = TextStyle(
                color = Black,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = if (isNumerical) KeyboardType.Number else KeyboardType.Text)
        )

        if (isHintDisplayed){
            Text(
                text = hint,
                style = TextStyle(
                    color = Grey
                ),
                fontSize = 24.sp,
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
            .clip(RoundedCornerShape(16.dp))
            .background(color = Grey)
            .clickable {
                onClick()
            }
    ){
        Row(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp)
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