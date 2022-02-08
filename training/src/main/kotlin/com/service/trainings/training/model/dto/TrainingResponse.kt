package com.service.trainings.training.model.dto

import java.math.BigDecimal

data class TrainingResponse(
    val id: Long,
    val name: String,
    val shortDescription: String,
    val fullDescription: String,
    val coach: String,
    val cost: BigDecimal,
    val lessons: List<LessonResponse>,
    var trainingImageUrl: String?
)
