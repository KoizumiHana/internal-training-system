package com.service.trainings.training

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class TrainingApplication

fun main(args: Array<String>) {
    runApplication<TrainingApplication>(*args)
}
