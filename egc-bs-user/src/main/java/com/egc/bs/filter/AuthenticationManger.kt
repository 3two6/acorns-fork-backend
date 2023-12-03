package com.egc.bs.filter

import com.egc.bs.entity.user.UserEntity
import com.egc.bs.entity.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component


@Component
class AuthenticationManger : AuthenticationManager {

    @Autowired
    lateinit var userRepository:UserRepository

    private val bCryptPasswordEncoder=BCryptPasswordEncoder(10)

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val password = authentication.credentials.toString()
        val user: UserEntity? = userRepository.findByEmail(authentication.name)
        if (!bCryptPasswordEncoder.matches(password, user?.password)) {
            throw BadCredentialsException("Bad credentials")
        }
//        if (user.isUsing2FA) {
//            val verificationCode: String = (authentication.details as Map<*, *>)["verificationCode"].toString()
//            val totp = Totp(user.secret)
//            when {
//                !isValidLong(verificationCode) -> throw BadCredentialsException("Invalid verfication code")
//                !totp.verify(verificationCode) -> throw BadCredentialsException("Invalid verfication code")
//            }
//        }
        val authorities = ArrayList<GrantedAuthority>()
//        if (user.roles != null) {
//            user.roles!!.forEach { authorities.add(SimpleGrantedAuthority(it.roleName)) }
//        }
        return UsernamePasswordAuthenticationToken(user?.email, user?.password, authorities)
    }

    private fun isValidLong(code: String): Boolean {
        try {
            code.toLong()
        } catch (e: NumberFormatException) {
            return false
        }
        return true
    }
}
