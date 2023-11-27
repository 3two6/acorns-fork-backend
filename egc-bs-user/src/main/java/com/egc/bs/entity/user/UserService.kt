package com.egc.bs.entity.user

import com.egc.baseapi.auth.PasswordUtil
import com.egc.baseapi.jpa.base.BaseService
import com.egc.baseapi.util.MyObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService:BaseService<UserRepository, UserEntity, RealUserModel>() {

    override val instance: UserEntity
        get() = UserEntity()
    override val modelClass: Class<UserEntity>
        get() = UserEntity::class.java
    override val realClass: Class<RealUserModel>
        get() =RealUserModel::class.java


    override fun getRealModel(primaryModel: UserEntity?): RealUserModel? {
        var result=super.getRealModel(primaryModel) as RealUserModel
        return result
    }


    fun resetPassword(id:Long):UserEntity?{
        val user=this.retrieveOne(id)
        user?.password= PasswordUtil.encode(PasswordUtil.INIT_PASSWORD)
        return user?.let {
            this.createOrUpdateOneWithModel(it,null)
        }
    }

    fun getByEmail(userEmail:String?):RealUserModel?{
//        val info=userId?.let {
//            this.repository.findByEmail(it, false)
//        }
//        if (info!=null)
//            if (info.size>0)
//                return this.getRealModel(info.get(0))
        val info=userEmail?.let {
            this.repository.findByEmail(it)
        }
        if (info!=null)
                return this.getRealModel(info)
        return null
    }


    fun verify(request: HttpServletRequest, response: HttpServletResponse, auth:Authentication):RealUserModel{
        val user=auth.principal as RealUserModel
        return user
    }

    fun changeUser(newUser:UserChangeModel, request: HttpServletRequest, response: HttpServletResponse, auth: Authentication):UserEntity?{
        val user=auth.principal as RealUserModel
        if (newUser.password!=null&&newUser.password!="") {
            if (!BCryptPasswordEncoder().matches(newUser?.password, user?.password)) {
                response.sendError(400, "You password is not correct")
                return null
            }
            if (newUser.newPassword==null||newUser.newPassword==""){
                response.sendError(400,"Pleas sign in with new password")
                return null
            }
            if (newUser.newPassword!!.length<6){
                response.sendError(400, "Password must be least 6 letters")
                return null
            }
            newUser.password=BCryptPasswordEncoder().encode(newUser.newPassword)
        }else newUser.password=user.password
        val refindedUser=UserEntity()
        MyObjectMapper.convert(newUser, refindedUser)
        return  this.createOrUpdateOneWithModel(refindedUser, request)
    }



}