package com.egc.bs.service

import com.egc.bs.entity.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserDetailServiceImpl:UserDetailsService {

    @Autowired
    var userService: UserService?=null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email:String): UserDetails {
        val user=this.userService?.getByEmail(email)
        user?:throw UsernameNotFoundException("You are not sign up")
        return User(email, user.password, true,true, true,true, emptyList())
    }

}