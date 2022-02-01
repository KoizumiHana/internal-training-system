package com.service.trainings.filestorage.service

import com.service.trainings.filestorage.model.entity.File
import java.util.*

interface FileService {

    fun save(file: File)

    fun getFile(uuid: UUID): File

    fun getAllLinksRelatedToResourceTypeByResourceId(resourceType: String, resourceId: String): List<String>

}