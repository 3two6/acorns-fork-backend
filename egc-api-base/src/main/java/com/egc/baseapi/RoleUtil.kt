package com.egc.baseapi

import org.springframework.security.core.Authentication
import javax.management.relation.RoleNotFoundException


class RoleUtil {

    companion object{
        fun assertRole(auth: Authentication?, roles:List<String>){
            auth?.let {
                val mines=auth.authorities.map{it.authority}
                if (mines.contains("admin")) return
                for (other in roles){
                    for (mine in mines){
                        if (mine.trim()==other)return
                    }
                }
                throw RoleNotFoundException("has no role")
            }?:throw RoleNotFoundException("Please sign in.")
        }


        fun hasRole(auth: Authentication?, roles: List<String>):Boolean{
            val mines= auth?.authorities?.map { it.authority }
            for (other in roles){
                for (mine in mines!!){
                    if (mine==other) return true
                }
            }
            return false
        }
    }

}