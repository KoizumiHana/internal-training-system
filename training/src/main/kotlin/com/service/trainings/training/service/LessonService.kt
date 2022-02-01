package com.service.trainings.training.service

import com.service.trainings.training.model.LessonCreateRequest
import com.service.trainings.training.model.dto.LessonResponse

interface LessonService {
    fun create(lessonCreateRequest: LessonCreateRequest)
    fun findById(id: Long): LessonResponse

}
