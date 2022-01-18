package com.service.trainings.training.controller

import com.service.trainings.training.model.dto.TrainingCreateRequest
import com.service.trainings.training.model.dto.TrainingUpdateRequest
import com.service.trainings.training.service.TrainingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping(
    value = ["/training"],
    consumes = [MediaType.APPLICATION_JSON_VALUE],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class TrainingController(@Autowired private val trainingService: TrainingService) {

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun createTraining(@Valid @RequestBody trainingCreateRequest: TrainingCreateRequest) =
        trainingService.create(trainingCreateRequest)

    @GetMapping("/{trainingId}")
    fun findById(@PathVariable(name = "trainingId") trainingId: Long) = trainingService.findById(trainingId)

    @GetMapping("/")
    fun findAll() = trainingService.findAll()

    @PutMapping("/")
    fun updateTraining(@RequestBody trainingUpdateRequest: TrainingUpdateRequest) =
        trainingService.update(trainingUpdateRequest)

    @DeleteMapping("/{trainingId}")
    fun deleteById(@PathVariable("trainingId") trainingId: Long) = trainingService.deleteById(trainingId)
}