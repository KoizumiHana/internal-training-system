package com.service.trainings.training.controller

import com.service.trainings.training.model.dto.TrainingCreateRequest
import com.service.trainings.training.model.dto.TrainingUpdateRequest
import com.service.trainings.training.service.TrainingFileStorageService
import com.service.trainings.training.service.TrainingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid


@RestController
@RequestMapping(value = ["/training"])
class TrainingController(
    @Autowired private val trainingService: TrainingService,
    @Autowired private val trainingFileStorageService: TrainingFileStorageService
) {

    @PostMapping("/", consumes = [MULTIPART_FORM_DATA_VALUE, APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestPart @Valid trainingCreateRequest: TrainingCreateRequest,
        @RequestPart trainingImage: MultipartFile
    ) = trainingFileStorageService.create(trainingCreateRequest, trainingImage)

    @GetMapping("/{trainingId}")
    fun findById(@PathVariable(name = "trainingId") trainingId: Long) = trainingFileStorageService.findById(trainingId)

    @GetMapping("/")
    fun findAll(
        @RequestParam("page", required = false, defaultValue = "0") page: Int,
        @RequestParam("size", required = false, defaultValue = "10") size: Int
    ) = trainingFileStorageService.findAll(page, size)

    @PutMapping("/", consumes = [MULTIPART_FORM_DATA_VALUE, APPLICATION_JSON_VALUE])
    fun update(
        @RequestPart @Valid trainingUpdateRequest: TrainingUpdateRequest,
        @RequestPart(required = false) trainingImage: MultipartFile?
    ) = trainingFileStorageService.update(trainingUpdateRequest, trainingImage)

    @DeleteMapping("/{trainingId}")
    fun deleteById(@PathVariable("trainingId") trainingId: Long) = trainingService.deleteById(trainingId)
}