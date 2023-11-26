package com.egc.baseapi.auth

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


class PasswordUtil {
    companion object{
        open val INIT_PASSWORD ="123456"

        fun encode(password:String):String?=BCryptPasswordEncoder().encode(password)

        fun compare(readable: String, unreadable:String):Boolean{
            return BCryptPasswordEncoder().matches(readable, unreadable)
        }
    }
}