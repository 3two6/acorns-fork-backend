package com.egc.baseapi.auth

import jakarta.servlet.http.HttpServletRequest


open class AuthUtil {

    companion object{
        const val REFERER="Referer"
        const val ORIGIN="Origin"

        fun getOrigin(request: HttpServletRequest):String{
            return request.getHeader(ORIGIN)
        }

        fun getReferer(request: HttpServletRequest):String{
            return request.getHeader(REFERER).substring(getOrigin(request).length)
        }

        fun getRequestModel(request: HttpServletRequest):ActionMethod{
            val method=ActionMethod().apply {
                this.requestUrl=request.requestURI
                this.method=request.method
            }
            return method
        }
    }

}