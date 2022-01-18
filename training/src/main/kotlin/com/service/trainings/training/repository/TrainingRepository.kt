package com.service.trainings.training.repository

import com.service.trainings.training.model.entity.Training
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainingRepository : JpaRepository<Training, Long>