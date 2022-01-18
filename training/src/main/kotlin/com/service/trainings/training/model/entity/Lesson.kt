package com.service.trainings.training.model.entity

import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
@Table(name = "lesson")
class Lesson(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
    @Column(name = "name") val name: String,
    @Column(name = "description") val description: String,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "training_id", nullable = false) val training: Training
) {
    constructor(
        name: String,
        description: String,
        training: Training
    ) : this(null, name, description, training)
}