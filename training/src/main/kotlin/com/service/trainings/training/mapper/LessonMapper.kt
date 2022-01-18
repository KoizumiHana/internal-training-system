package com.service.trainings.training.mapper

import com.service.trainings.training.model.LessonCreateRequest
import com.service.trainings.training.model.LessonRequest
import com.service.trainings.training.model.LessonUpdateRequest
import com.service.trainings.training.model.dto.LessonResponse
import com.service.trainings.training.model.entity.Lesson
import com.service.trainings.training.model.entity.Training


fun LessonRequest.toEntity(training: Training) = when (this) {
    is LessonCreateRequest -> Lesson(
        name = name,
        description = description,
        training = training
    )
    is LessonUpdateRequest -> Lesson(
        id = id,
        name = name,
        description = description,
        training = training
    )
}

fun Lesson.toResponse() = LessonResponse(
    id = id!!,
    name = name,
    description = description
)
