package com.service.trainings.training

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
class TrainingApplication

fun main(args: Array<String>) {
    runApplication<TrainingApplication>(*args)
}
