package com.service.trainings.training.service

import com.service.trainings.training.mapper.toEntity
import com.service.trainings.training.mapper.toResponse
import com.service.trainings.training.model.LessonCreateRequest
import com.service.trainings.training.model.dto.LessonResponse
import com.service.trainings.training.model.entity.Lesson
import com.service.trainings.training.repository.LessonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class LessonServiceImpl(
    @Autowired private val lessonRepository: LessonRepository,
    @Autowired private val trainingService: TrainingService
) : LessonService {

    override fun create(lessonCreateRequest: LessonCreateRequest) {
        val relatedTraining = trainingService.findEntityById(lessonCreateRequest.trainingId)
        lessonRepository.save(lessonCreateRequest.toEntity(relatedTraining))
    }

    override fun findById(id: Long): LessonResponse =
        lessonRepository.findById(id).map(Lesson::toResponse)
            .orElseThrow { EntityNotFoundException("lesson with id=$id not found") }

}