package com.service.trainings.training.mapper

import com.service.trainings.training.model.dto.TrainingCreateRequest
import com.service.trainings.training.model.dto.TrainingRequest
import com.service.trainings.training.model.dto.TrainingResponse
import com.service.trainings.training.model.dto.TrainingUpdateRequest
import com.service.trainings.training.model.entity.Lesson
import com.service.trainings.training.model.entity.Training

fun TrainingRequest.toEntity() = when (this) {
    is TrainingUpdateRequest -> Training(
        id = id,
        name = name,
        coach = coach,
        cost = cost,
        shortDescription = shortDescription,
        fullDescription = fullDescription,
        lessons = emptyList()
    )
    is TrainingCreateRequest -> Training(
        name = name,
        coach = coach,
        cost = cost,
        shortDescription = shortDescription,
        fullDescription = fullDescription
    )
}

fun Training.toResponse() = TrainingResponse(
    id = id!!,
    name = name,
    shortDescription = shortDescription,
    fullDescription = fullDescription,
    cost = cost,
    coach = coach,
    lessons = lessons.map(Lesson::toResponse),
    trainingImageUrl = null
)