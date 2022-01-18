package com.service.trainings.training.model.entity

import java.math.BigDecimal
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
@Table(name = "training")
class Training(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
    @Column(name = "name") val name: String,
    @Column(name = "short_description") val shortDescription: String,
    @Column(name = "full_description") val fullDescription: String,
    @Column(name = "coach") val coach: String,
    @Column(name = "cost") val cost: BigDecimal,
    @OneToMany(mappedBy = "training", fetch = LAZY) val lessons: List<Lesson>
) {
    constructor(
        name: String,
        cost: BigDecimal,
        shortDescription: String,
        fullDescription: String,
        coach: String,
    ) : this(null, name, shortDescription, fullDescription, coach, cost, emptyList())
}