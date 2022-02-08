package com.service.trainings.filestorage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableDiscoveryClient
class FileStorageApplication

fun main(args: Array<String>) {
    runApplication<FileStorageApplication>(*args)
}
