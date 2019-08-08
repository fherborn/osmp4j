package com.osmp4j.host.controller

import com.osmp4j.ftp.FtpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.util.*

@RestController
@RequestMapping("test")
class TestController @Autowired constructor(private val ftpService: FtpService) {

    @GetMapping("/ftp")
    fun testFTP() {
        val file = File("${UUID.randomUUID()}.txt")
        file.createNewFile()
        file.writeText("Hallo123")
        ftpService.upload("test.txt", file)
        file.delete()

        val outFile = File("output.txt")
        ftpService.donwload("test.txt", outFile)
        println(file.useLines { it.firstOrNull() })
    }
}