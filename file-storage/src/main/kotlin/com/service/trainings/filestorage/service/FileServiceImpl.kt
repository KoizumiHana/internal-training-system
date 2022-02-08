package com.service.trainings.filestorage.service

import com.service.trainings.filestorage.model.entity.File
import com.service.trainings.filestorage.repository.FileRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class FileServiceImpl(@Autowired val fileRepository: FileRepository) : FileService {

    override fun create(file: File) {
        fileRepository.save(file)
    }

    override fun findByUUID(uuid: UUID): File? {
        return fileRepository.findByUuid(uuid)
    }

    override fun deleteByUUID(uuid: UUID): Unit = fileRepository.deleteByUuid(uuid)

    override fun getAllLinksRelatedToResourceTypeByResourceId(resourceType: String, resourceId: String): List<String> =
        fileRepository.findAllDownloadLinksByResourceTypeAndResourceId(resourceType, resourceId)
}