package com.egc.baseapi.util

import jakarta.servlet.http.HttpServletRequest
import org.apache.commons.lang.StringUtils
import org.slf4j.LoggerFactory

object IPUtils {
    private val logger = LoggerFactory.getLogger(IPUtils::class.java)

    /*

    Get IP address
    If u use a reverse proxy as Nginx, u cannot obtain the IP address with request.getRemoteAddr
    If u use a multi-reverse-proxy , the value of X=Forwarded-For is not one, but a chain of IPs.
    If the first character in X-Forwarded-For is not unknown but a valid ip string, it's an actual ip address

*/
    fun getIpAddr(request: HttpServletRequest): String? {
        var ip: String? = null
        try {
            ip = request.getHeader("x-forwarded-for")
            if (StringUtils.isEmpty(ip) || "unknown".equals(ip, ignoreCase = true)) {
                ip = request.getHeader("Proxy-Client-IP")
            }
            if (StringUtils.isEmpty(ip) || ip.length == 0 || "unknown".equals(ip, ignoreCase = true)) {
                ip = request.getHeader("WL-Proxy-Client-IP")
            }
            if (StringUtils.isEmpty(ip) || "unknown".equals(ip, ignoreCase = true)) {
                ip = request.getHeader("HTTP_CLIENT_IP")
            }
            if (StringUtils.isEmpty(ip) || "unknown".equals(ip, ignoreCase = true)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR")
            }
            if (StringUtils.isEmpty(ip) || "unknown".equals(ip, ignoreCase = true)) {
                ip = request.remoteAddr
            }
            if (ip.contains(",")) {
                ip = ip.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
            }
        } catch (e: Exception) {
            logger.error("IPUtils Error", e)
        }
        return ip
    }
}