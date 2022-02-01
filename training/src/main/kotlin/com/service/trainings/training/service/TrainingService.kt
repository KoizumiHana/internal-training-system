package com.service.trainings.training.service

import com.service.trainings.training.model.dto.TrainingCreateRequest
import com.service.trainings.training.model.dto.TrainingResponse
import com.service.trainings.training.model.dto.TrainingUpdateRequest
import com.service.trainings.training.model.entity.Training

interface TrainingService {
    fun create(trainingCreateRequest: TrainingCreateRequest)
    fun findById(trainingId: Long): TrainingResponse
    fun findAll(): List<TrainingResponse>
    fun update(trainingUpdateRequest: TrainingUpdateRequest)
    fun deleteById(trainingId: Long)
    fun findEntityById(trainingId: Long): Training
}
