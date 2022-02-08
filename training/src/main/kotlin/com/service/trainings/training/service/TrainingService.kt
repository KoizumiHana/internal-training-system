package com.service.trainings.training.service

import com.service.trainings.training.model.dto.TrainingCreateRequest
import com.service.trainings.training.model.dto.TrainingResponse
import com.service.trainings.training.model.dto.TrainingUpdateRequest
import com.service.trainings.training.model.entity.Training
import org.springframework.data.domain.Pageable
import org.springframework.web.multipart.MultipartFile

interface TrainingService {
    fun create(trainingCreateRequest: TrainingCreateRequest): Training
    fun findById(trainingId: Long): TrainingResponse
    fun findAll(): List<TrainingResponse>
    fun findAll(pageable: Pageable): List<TrainingResponse>
    fun update(trainingUpdateRequest: TrainingUpdateRequest): Training
    fun deleteById(trainingId: Long)
    fun findEntityById(trainingId: Long): Training
}
