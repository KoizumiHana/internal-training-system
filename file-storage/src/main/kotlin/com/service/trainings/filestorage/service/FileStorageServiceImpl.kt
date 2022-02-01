package com.service.trainings.filestorage.service

import com.amazonaws.services.s3.model.S3Object
import com.service.trainings.filestorage.model.entity.File
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*
import javax.transaction.Transactional

@Service
class FileStorageServiceImpl(
    @Autowired val fileService: FileService, @Autowired val storageService: StorageService
) : FileStorageService {

    @Transactional
    override fun saveFile(multipartFile: MultipartFile, resourceType: String, resourceId: String) {
        val uuid = UUID.randomUUID()
        val originFilename = multipartFile.originalFilename!!
        val downloadLink = "/storage/download/$uuid"
        val file = File(
            uuid = uuid,
            resourceType = resourceType,
            resourceId = resourceId,
            originName = originFilename,
            downloadLink = downloadLink
        )
        fileService.save(file)
        storageService.uploadObject(bucketName = resourceType, filePath = "$resourceId/$uuid", file = multipartFile)
    }

    override fun getListOfLinksRelatedToResource(resourceType: String, resourceId: String): List<String> =
        fileService.getAllLinksRelatedToResourceTypeByResourceId(resourceType, resourceId)


    override fun downloadFileByUUID(uuid: String): S3Object? {
        val file = fileService.getFile(UUID.fromString(uuid))
        val bucketName = file.resourceType
        val filePath = "${file.resourceId}/${file.uuid}"
        val s3Object = storageService.downloadByBucketNameAndFilePath(bucketName, filePath)
        s3Object!!.key = file.originName
        return s3Object
    }

}