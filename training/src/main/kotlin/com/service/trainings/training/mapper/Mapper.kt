package com.service.trainings.training.mapper

interface Mapper<ENTITY, REQ, RESP> {
    fun toEntity(request: REQ): ENTITY
    fun toResponseDto(entity: ENTITY): RESP
}