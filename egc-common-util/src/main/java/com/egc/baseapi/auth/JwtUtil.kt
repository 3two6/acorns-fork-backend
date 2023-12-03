package com.egc.baseapi.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import java.util.*
import javax.crypto.SecretKey

class JwtUtil {

    companion object{
        var SININGKEY="PseudoSecret-Pseudosecret-IMPORTANT-Please-Use-Ur-Own-Key-IMPORTANT"
        var KEY:SecretKey?= Keys.hmacShaKeyFor(SININGKEY.toByteArray())
        const val PREFIX="Bearer "
        const val authHeaderField="Authorization"

        const val EXPIRATIONTIME:Long=86_400_000L // 1 day in milliseconds

        fun getToken(subject:String):String?{
            return Jwts.builder().subject(subject).expiration(Date(System.currentTimeMillis()+ EXPIRATIONTIME))
                .signWith(KEY).compact()
        }

        fun parseToken(request: HttpServletRequest): Jws<Claims>? {
            val token=request.getHeader(authHeaderField)?:throw BadCredentialsException("Please signin.")
            val claims=Jwts.parser()
                .verifyWith(KEY)
                .clockSkewSeconds(3 * 60)
                .build()
                .parseSignedClaims(token.replace(PREFIX, ""))
            return claims
        }

        fun createToken(authentication: Authentication): String {
            val authClaims: MutableList<String> = mutableListOf()
            authentication.authorities?.let { authorities ->
                authorities.forEach { claim -> authClaims.add(claim.toString()) }
            }

            return Jwts.builder()
                .subject(authentication.name)
                .claim("auth", authClaims)
                .expiration(Date(System.currentTimeMillis()+ EXPIRATIONTIME))
                .signWith(KEY)
                .compact()
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