package com.egc.bs.upload

import io.swagger.v3.oas.annotations.Operation
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
class UploadController {

    @Autowired
    lateinit var uploadService: UploadService

    @PostMapping("/upload")
    @Operation(summary =  "file upload")
    fun uploadImage(@RequestParam("file") fileInfo:MultipartFile, request: HttpServletRequest?):String?{
        try {
            return this.uploadService.saveAttachment(fileInfo,"upload",request)
        } catch (e:Exception){
            throw Exception(e)
        }
    }

}