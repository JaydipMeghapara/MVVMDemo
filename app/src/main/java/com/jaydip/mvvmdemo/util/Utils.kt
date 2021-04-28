package com.jaydip.mvvmdemo.util

import android.annotation.SuppressLint
import android.location.Location
import android.os.Handler
import android.text.format.DateUtils
import android.view.View
import android.widget.ProgressBar
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.ArrayList
import kotlin.math.max

object Utils {
    fun isValidName(name: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val namePattern =
            "^[A-Za-z. ]{2,50}\$"
        pattern = Pattern.compile(namePattern)
        matcher = pattern.matcher(name)
        return matcher.matches()


    }

    fun isValidEmail(email: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val emailPattern =
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        pattern = Pattern.compile(emailPattern)
        matcher = pattern.matcher(email)
        return matcher.matches()

    }


    fun isValidPassword(password: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val passwordPattern =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^!)(&+=*])(?=\\S+\$).{8,}\$"
        pattern = Pattern.compile(passwordPattern)
        matcher = pattern.matcher(password)
        return matcher.matches()

    }

    fun isValidMobileNumber(password: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val mobileNumberPattern =
            "^[+]?[0-9]{10,13}\$"
        pattern = Pattern.compile(mobileNumberPattern)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }

    fun degreeLat(latitude: Double): String? {
        val builder = StringBuilder()

        val latitudeDegrees: String = Location.convert(Math.abs(latitude), Location.FORMAT_SECONDS)
        val latitudeSplit = latitudeDegrees.split(":").toTypedArray()
        builder.append(latitudeSplit[0])
        builder.append("°")
        builder.append(latitudeSplit[1])
        builder.append("'")
        val number2digits: Double = Math.round(latitudeSplit[2].toDouble() * 100.0) / 100.0
        builder.append(number2digits)
        builder.append("\"")
//        builder.append(" ")
        if (latitude < 0) 
            builder.append("S ")
         else 
            builder.append("N ")
        
        return builder.toString()
    }

    fun degreeLng(longitude: Double): String? {
        val builder = StringBuilder()

        val longitudeDegrees: String =
            Location.convert(Math.abs(longitude), Location.FORMAT_SECONDS)
        val longitudeSplit = longitudeDegrees.split(":").toTypedArray()
        builder.append(longitudeSplit[0])
        builder.append("°")
        builder.append(longitudeSplit[1])
        builder.append("'")
        val number2digits: Double = Math.round(longitudeSplit[2].toDouble() * 100.0) / 100.0
        builder.append(number2digits)
        builder.append("\"")
        if (longitude < 0) {
            builder.append("W ")
        } else {
            builder.append("E ")
        }
        return builder.toString()
    }

    fun hideProgressBar(p: ProgressBar) {
        Handler().postDelayed({
            if (p.visibility == View.VISIBLE) {
                p.visibility = View.GONE
            }
        }, 500)
    }

    fun showProgressBar(p: ProgressBar) {
        Handler().postDelayed({
            if (p.visibility != View.VISIBLE) {
                p.visibility = View.VISIBLE
            }
        }, 500)
    }


    @SuppressLint("SimpleDateFormat")
    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun getTimeSlot(date: String, startTime: String, endTime: String): ArrayList<String> {
        var firstTime = startTime
        val currentSdf = SimpleDateFormat("HH:mm")
        val selectedDateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val currentTime = currentSdf.format(Date())

        val currentDay = currentTime.split(":").toTypedArray()
        val startDay = startTime.split(":").toTypedArray()

        val format = "yyyy-MM-dd HH:mm"
        val list = ArrayList<String>()
        val finalList = ArrayList<String>()
        val outputList = ArrayList<String>()

        val sdf = SimpleDateFormat(format)

        try {
            val selectedDate =selectedDateFormat.parse(date)

            if (startTime < currentTime) {
                if ( DateUtils.isToday(selectedDate.time)) {

                    firstTime = if (currentDay[1] < 15.toString()) {

                        currentDay[0] + ":15"
                    } else if (currentDay[1] < 16.toString() || currentDay[1] < 30.toString()) {
                        currentDay[0] + ":30"
                    } else if (currentDay[1] < 31.toString() || currentDay[1] < 45.toString()) {
                        currentDay[0] + ":45"
                    } else {
                        var intDay: Int = currentDay[0].toInt()
                        intDay++
                        "$intDay:00"
                    }
                }else{
                    firstTime = if (startDay[1] < 15.toString()) {
                        startDay[0] + ":15"
                    } else if (startDay[1] < 16.toString() || startDay[1] < 30.toString()) {
                        startDay[0] + ":30"
                    } else if (startDay[1] < 31.toString() || startDay[1] < 45.toString()) {
                        startDay[0] + ":45"
                    } else {
                        var intDay: Int = startDay[0].toInt()
                        intDay++
                        "$intDay:00"
                    }
                }
            } else {
                firstTime = if (startDay[1] < 15.toString()) {
                    startDay[0] + ":15"
                } else if (startDay[1] < 16.toString() || startDay[1] < 30.toString()) {
                    startDay[0] + ":30"
                } else if (startDay[1] < 31.toString() || startDay[1] < 45.toString()) {
                    startDay[0] + ":45"
                } else {
                    var intDay: Int = startDay[0].toInt()
                    intDay++
                    "$intDay:00"
                }
            }

            val dateObj1 = sdf.parse("$date $firstTime")
            val dateObj2 = sdf.parse("$date $endTime")
            val twoTimeDiff = dateObj2.time - dateObj1.time

            var dif = dateObj1.time
            while (dif <= dateObj2.time) {
                val slot = Date(dif).toString()
                dif += 900000
                val day = slot.split(":").toTypedArray()
                val finalDate = day[0].substring(max(day[0].length - 2, 0)) + ":" + day[1]
                list.add(finalDate)
            }
            var counter = 0
            if(twoTimeDiff > 7200000){
                for (listItem in list){
                    if (counter < 8){
                        finalList.add(listItem)
                    }else {
                        if (counter % 2 == 0)
                            finalList.add(list[counter])
                    }
                    counter++
                }
                outputList.addAll(finalList)
            }else{
                outputList.addAll(list)
            }

        }catch (e :Exception){
            e.message
        }
        println("finalList $outputList")
        return outputList
    }

}