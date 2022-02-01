package com.service.trainings.filestorage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class FileStorageApplication

fun main(args: Array<String>) {
    runApplication<FileStorageApplication>(*args)
}
