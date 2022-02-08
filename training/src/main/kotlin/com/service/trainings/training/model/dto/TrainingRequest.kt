package com.service.trainings.training.model.dto

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

sealed class TrainingRequest(
    @field:NotBlank val name: String,
    @field:NotBlank val shortDescription: String,
    @field:NotBlank val fullDescription: String,
    @field:NotBlank val coach: String,
    @field:PositiveOrZero val cost: BigDecimal,
)

class TrainingCreateRequest(
    name: String,
    shortDescription: String,
    fullDescription: String,
    coach: String,
    cost: BigDecimal,
) : TrainingRequest(name, shortDescription, fullDescription, coach, cost)

class TrainingUpdateRequest(
    @field:Positive val id: Long,
    val trainingImageURL: String?,
    name: String,
    shortDescription: String,
    fullDescription: String,
    coach: String,
    cost: BigDecimal
) : TrainingRequest(name, shortDescription, fullDescription, coach, cost)
