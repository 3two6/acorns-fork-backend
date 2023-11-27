package com.egc.bs.upload

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedOutputStream
import java.io.FileOutputStream
import java.util.*


@Service
class UploadService {

    @Value("\${userfront.uploadpath}")
    var uploadPath:String=""

    @Throws(Exception::class)
    fun saveAttachment(file:MultipartFile?, dir:String, request: HttpServletRequest?):String?{
        if (file!=null){
            try {
                val fileName= Date().time.toString()+"."+file.originalFilename.substringAfter(".")
                val path="$uploadPath/$fileName"
                print("file upload path: $path")
                val barr=file.bytes

                val bout=BufferedOutputStream(FileOutputStream(path))
                bout.write(barr)
                bout.flush()
                bout.close()
                print("It was uploaded correctly")
                return "/user_upload/$fileName"
            } catch (e:Exception){
                print("upload error$e")
            }
        }
        throw Exception("Uploaded file empty")
    }


}