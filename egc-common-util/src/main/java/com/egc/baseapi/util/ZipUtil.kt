package com.egc.baseapi.util

import org.apache.tomcat.util.http.fileupload.IOUtils
import java.io.*
import java.lang.Exception
import java.lang.RuntimeException
import java.util.Enumeration
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

class ZipUtil {

    open fun unzip(archive:String, outputDir: File){
        try {
            val zipFile=ZipFile(archive)
            val e:Enumeration<*> = zipFile.entries()
            while (e.hasMoreElements()){
                val entry=e.nextElement() as ZipEntry
                unzipEntry(zipFile, entry, outputDir)
            }
        } catch (e:Exception){

        }
    }

    @Throws(IOException::class)
    private fun unzipEntry(zipFile: ZipFile, entry: ZipEntry,outputDir: File){
        if (entry.isDirectory){
            createDirectory(File(outputDir, entry.name))
            return
        }
        val outputFile=File(outputDir,entry.name)
        if (!outputFile.parentFile.exists()){
            createDirectory(outputFile.parentFile)
        }
        val inputStream=BufferedInputStream(zipFile.getInputStream(entry))
        val outputStream=BufferedOutputStream(FileOutputStream(outputFile))

        try {
            IOUtils.copy(inputStream, outputStream)
        }catch (e:Exception){

        }finally {
            outputStream.close()
            inputStream.close()
        }
    }


    private fun createDirectory(dir:File){
        if (!dir.exists()){
            if (!dir.mkdir()) throw RuntimeException("Can't create directory $dir")
            else{

            }
        }
    }

}