package com.egc.bs.login


import com.egc.baseapi.auth.JwtUtil
import com.egc.baseapi.util.IPUtils
import com.egc.bs.entity.user.UserEntity
import com.egc.bs.entity.user.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class LoginService {

    @Autowired
    lateinit var userService: UserService

    fun login(userInfo:UserEntity, request: HttpServletRequest, response: HttpServletResponse):String?{
        val info=userInfo.email?.let {
            this.userService.getByEmail(it)
        }
        if (info==null){
            response.sendError(400,"You are not sign up")
            return ""
        }
        if (!BCryptPasswordEncoder().matches(userInfo.password, info?.password)){
            response.sendError(400,"Your password is not correct.")
            return ""
        }
        val user=info?.email?.let {
            this.userService.getByEmail(it)
        }
        val reqIp= IPUtils.getIpAddr(request)
        val res=info.email?.let {
            JwtUtil.setToken(response,it,request)
        }
        return res
    }


    fun register(userInfo: UserEntity, request: HttpServletRequest, response: HttpServletResponse):UserEntity?{
//        val info=userInfo.email?.let {
//            this.userService.getByEmail(it)
//        }
//        if (info!=null){
//            response.sendError(400,"You are already sign up")
//            return null
//        }
        userInfo.password=BCryptPasswordEncoder().encode(userInfo.password)
        return this.userService.createOrUpdateOneWithModel(userInfo,request)
    }




}