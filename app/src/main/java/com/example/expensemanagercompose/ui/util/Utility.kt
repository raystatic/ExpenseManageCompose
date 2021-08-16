package com.example.expensemanagercompose.ui.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Utility {

    fun formatDate(date:String): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
        val d = getDate(date)
        sdf.timeZone =  TimeZone.getDefault()
        return sdf.format(d)
    }

    private fun getDate(d: String):Date{
        val sdf = DateFormat.getDateTimeInstance(
            DateFormat.FULL,
            DateFormat.FULL
        ) as SimpleDateFormat
        sdf.applyPattern("yyyy-MM-dd")
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.parse(d)
    }

}