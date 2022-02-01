package com.service.trainings.filestorage.model.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "file")
class File(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
    @Column(name = "uuid") val uuid: UUID,
    @Column(name = "resource_type") val resourceType: String,
    @Column(name = "resource_id") val resourceId: String,
    @Column(name = "origin_name") val originName: String,
    @Column(name = "download_link") val downloadLink: String
) {
    constructor(
        uuid: UUID,
        resourceType: String,
        resourceId: String,
        originName: String,
        downloadLink: String
    ) : this(null, uuid, resourceType, resourceId, originName, downloadLink)
}