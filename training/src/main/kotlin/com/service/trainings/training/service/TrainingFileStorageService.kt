package com.service.trainings.training.service

import com.service.trainings.training.integration.FileStorageFeignClient
import com.service.trainings.training.model.dto.TrainingCreateRequest
import com.service.trainings.training.model.dto.TrainingResponse
import com.service.trainings.training.model.dto.TrainingUpdateRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import javax.transaction.Transactional

private const val TRAINING = "training"

@Service
class TrainingFileStorageService(
    @Autowired private val trainingService: TrainingService,
    @Autowired private val fileStorageFeignClient: FileStorageFeignClient
) {

    @Transactional
    fun create(
        trainingRequest: TrainingCreateRequest,
        trainingImage: MultipartFile
    ) {
        val createdTraining = trainingService.create(trainingRequest)
        fileStorageFeignClient.uploadFile(trainingImage, TRAINING, createdTraining.id.toString())
    }

    fun findById(trainingId: Long): TrainingResponse {
        val trainingResponse = trainingService.findById(trainingId)
        val imageRelatedToTraining =
            fileStorageFeignClient.getAllLinksRelatedToResource(TRAINING, trainingId.toString()).firstOrNull()
        trainingResponse.trainingImageUrl = imageRelatedToTraining
        return trainingResponse
    }

    fun findAll(page: Int, size: Int): List<TrainingResponse> {
        val trainings = trainingService.findAll(PageRequest.of(page, size))
        val trainingIds = trainings.map { it.id.toString() }
        val linksRelatedToResources = fileStorageFeignClient.getAllLinksRelatedToResources(TRAINING, trainingIds)
        trainings.map { it.trainingImageUrl = linksRelatedToResources[it.id.toString()]?.firstOrNull() }
        return trainings
    }

    @Transactional
    fun update(
        trainingRequest: TrainingUpdateRequest,
        trainingImage: MultipartFile?
    ) {
        val updatedTraining = trainingService.update(trainingRequest)
        trainingImage ?: return

        trainingRequest.trainingImageURL?.let {
            fileStorageFeignClient.deleteFileByUUID(it.split("/").last())
        }
        fileStorageFeignClient.uploadFile(trainingImage, TRAINING, updatedTraining.id.toString())

    }
}