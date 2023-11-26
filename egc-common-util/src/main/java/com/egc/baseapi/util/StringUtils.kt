package com.egc.baseapi.util

class StringUtils {

    companion object{

        fun format2String(str:String):String{
            if (str.length>1) return str
            return "0$str"
        }

    }

}