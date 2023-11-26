package com.egc.baseapi.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import java.util.regex.Pattern
import kotlin.math.min
import kotlin.math.pow

object NumberUtils {
    fun isint(num: Double): Boolean {
        return num.toInt().toDouble() == num
    }

    /*
    * Determine whether the character value is numeric value format
    * @param str
    * @return
    * */
    fun isDigit(str: String?): Boolean {
        return if (str == null || str.trim { it <= ' ' } == "") {
            false
        } else str.matches("^\\d+$".toRegex())
    }

    /*
    * Trim a decimal to a specified number of digits
    * @param num
    * @parse scale
    * @return
    * */
    fun scale(num: Double, scale: Int): Double {
        val bd = BigDecimal(num)
        return bd.setScale(scale, RoundingMode.HALF_UP).toDouble()
    }

    /*
    Returns an array of number obtained from a string using a regular expression
*/
    fun searchNumber(value: String?, regex: String?): Array<Double> {
        val doubles: MutableList<Double> = ArrayList()
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(value)
        if (matcher.find()) {
            val result = matcher.toMatchResult()
            for (i in 1..result.groupCount()) {
                doubles.add(result.group(i).toDouble())
            }
        }
        return doubles.toTypedArray<Double>()
    }

    /*
    * Generate a random number with a specified number of digits
    * @param len
    * @return
    * */
    fun generateCode(len: Int): String {
        var len = len
        len = min(len.toDouble(), 8.0).toInt()
        val min: Int = 10.0.pow((len - 1).toDouble()).toInt()
        val num: Int = Random().nextInt(10.0.pow((len + 1).toDouble()).toInt() - 1) + min
        return num.toString().substring(0, len)
    }

    /*
    * Convert to the decimal numbers to string
    * @param src The number u wanna return
    * @param len How many digits is converted, if not enough, fill with 0.
    * @return A string representing a number in decimal
    * */
    fun formatInt(src: Int, len: Int): String {
        val str = src.toString()
        val lenOrg = str.length
        var dest = ""
        return if (len > lenOrg) {
            for (i in 0 until len - lenOrg) {
                dest += "0"
            }
            dest += str
            dest
        } else {
            str
        }
    }
}