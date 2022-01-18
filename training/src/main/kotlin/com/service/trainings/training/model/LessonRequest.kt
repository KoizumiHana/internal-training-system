package com.service.trainings.training.model

import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

sealed class LessonRequest(
    @field:NotBlank val name: String,
    @field:NotBlank val description: String,
)

class LessonCreateRequest(
    name: String,
    description: String,
    @field:PositiveOrZero val trainingId: Long
) : LessonRequest(name, description)

class LessonUpdateRequest(
    @field:PositiveOrZero val id: Long,
    name: String,
    description: String,
) : LessonRequest(name, description)


