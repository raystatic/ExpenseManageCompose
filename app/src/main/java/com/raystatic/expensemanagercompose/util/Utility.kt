package com.raystatic.expensemanagercompose.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
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

    fun getDateInIso(d: LocalDate): String {
        val df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'")
        return d.atStartOfDay().atOffset(ZoneOffset.UTC).format(df)
    }

    fun getMonthFromDate(d:String): String {
        return android.text.format.DateFormat.format("MMM", getDate(d)).toString()
    }

    fun getDateFromDate(d:String):String{
        return android.text.format.DateFormat.format("dd", getDate(d)).toString()
    }

}