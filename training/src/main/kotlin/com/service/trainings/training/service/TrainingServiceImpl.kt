package com.service.trainings.training.service

import com.service.trainings.training.mapper.toEntity
import com.service.trainings.training.mapper.toResponse
import com.service.trainings.training.model.dto.TrainingCreateRequest
import com.service.trainings.training.model.dto.TrainingResponse
import com.service.trainings.training.model.dto.TrainingUpdateRequest
import com.service.trainings.training.model.entity.Training
import com.service.trainings.training.repository.TrainingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class TrainingServiceImpl(@Autowired private val trainingRepository: TrainingRepository) : TrainingService {

    override fun create(trainingCreateRequest: TrainingCreateRequest) {
        val trainingEntity = trainingCreateRequest.toEntity()
        trainingRepository.save(trainingEntity)
    }

    override fun findById(trainingId: Long) = findEntityById(trainingId).toResponse()

    override fun findEntityById(trainingId: Long): Training = trainingRepository.findById(trainingId)
        .orElseThrow { EntityNotFoundException("training with id=$trainingId not found") }


    override fun findAll(): List<TrainingResponse> = trainingRepository.findAll().map(Training::toResponse)

    override fun update(trainingUpdateRequest: TrainingUpdateRequest) {
        TODO("Not yet implemented")
    }

    override fun deleteById(trainingId: Long) {
        trainingRepository.deleteById(trainingId)
    }
}