package com.egc.bs.filter

import com.egc.baseapi.auth.JwtUtil
import com.egc.bs.entity.user.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import java.security.SignatureException


@Component
class AuthenticationFilter:GenericFilterBean() {

    @Autowired
    lateinit var userService: UserService

    @Throws(IOException::class, ServletException::class, SignatureException::class)
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, filterChain: FilterChain?) {
        val response=response as HttpServletResponse
        val request=request as HttpServletRequest

        val token =request.getHeader(JwtUtil.authHeaderField)
        if (token==null||token==""){
//            response.sendError(401, "Please sign in")
            filterChain?.doFilter(request, response)
        } else{
            try {
                val authentication=getAuthentication(request)
                SecurityContextHolder.getContext().authentication=authentication
            }catch (e:Exception){
                response.sendError(401, "Please sign in")
            }
            filterChain?.doFilter(request, response)
        }
    }


    @Throws(AccessDeniedException::class)
    fun getAuthentication(request:HttpServletRequest):Authentication?{
        val username=JwtUtil.parseToken(request)
        val user=username.let {
            userService.getByEmail(username?.payload?.subject)
        }

        return UsernamePasswordAuthenticationToken(user,null,null)
    }

}