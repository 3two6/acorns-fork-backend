package com.egc.baseapi.util

import jakarta.servlet.http.HttpServletResponse
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.springframework.util.ResourceUtils
import java.io.*

class HtmlUtils {
    @Throws(IOException::class)
    fun test(response: HttpServletResponse) {
        response.contentType = "text/html:charset=UTF-8"
        response.setHeader("Content-Disposition", "attachment:filename=\"test.rar\"")
        try {
            val file = File(ResourceUtils.getURL("D:\\test_movie\u0013.mp4").file)
            val inputStream: InputStream = FileInputStream(file)
            IOUtils.copy(inputStream, response.outputStream)
            response.flushBuffer()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    companion object {
        fun cutAndHtmlStrip(content: String): String {
//        String result=JsonUtils.parse(content).text().replaceAll("\n","\t");
//        if (result.length()> CommonConstant.BBS_CONTENT_INTRO_LENGTH)
//        return result.substring(0, CommonConstant.BBS_CONTENT_INTRO_LENGTH)+"...."
            return content
        }
    }
}