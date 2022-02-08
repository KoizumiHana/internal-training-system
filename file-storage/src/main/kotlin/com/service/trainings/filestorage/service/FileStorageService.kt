package com.service.trainings.filestorage.service

import com.amazonaws.services.s3.model.S3Object
import org.springframework.web.multipart.MultipartFile

interface FileStorageService {

    fun saveFile(multipartFile: MultipartFile, resourceType: String, resourceId: String)
    fun downloadFileByUUID(uuid: String): S3Object?
    fun getListOfLinksRelatedToResource(resourceType: String, resourceId: String): List<String>
    fun deleteFileByUUID(uuid: String)
}