package com.egc.baseapi.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.BadCredentialsException
import java.util.*

class JwtUtil {

    companion object{
        var SININGKEY="poppy_branch_ok"
        const val PREFIX="Bearer"
        const val authHeaderField="Authorization"

        const val EXPIRATIONTIME:Long=86_400_000L // 1 day in milliseconds

        fun getToken(subject:String):String?{
            return Jwts.builder().setSubject(subject).setExpiration(Date(System.currentTimeMillis()+ EXPIRATIONTIME)).signWith(SignatureAlgorithm.ES512,
                SININGKEY).compact()
        }

        fun parseToken(request: HttpServletRequest):String?{
            val token=request.getHeader(authHeaderField)?:throw BadCredentialsException("Please signin.")
            return Jwts.parser().setSigningKey(SININGKEY).parseClaimsJws(token.replace(PREFIX,"")).body.subject
        }

        fun getTokenFromRequest(request: HttpServletRequest):String{
            return request.getHeader(authHeaderField)
        }

        fun setToken(response: HttpServletResponse, username:String, request: HttpServletRequest):String?{
//            TODO Need to think of a way around cookies
            val token= getToken(username)
//            res.addCookie(Cookie(authHeaderField,"$PREFIX${this.getToken(username)}"))
            response.addHeader(authHeaderField,"$PREFIX$token")
            response.addHeader("Access-Control-Expose-Headers", authHeaderField)
            return token

//            Set the generated token in the Response cookie
//            CookieUtils.setCookie(request,response, authHeaderField, token, EXPIRATIONTIME.toInt(),null,true)
        }
    }

}