package com.service.trainings.filestorage.repository

import com.service.trainings.filestorage.model.entity.File
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FileRepository : JpaRepository<File, Long> {

    fun findByUuid(uuid: UUID): File

    @Query("select f.downloadLink from File f where f.resourceType = ?1 and f.resourceId = ?2")
    fun findAllDownloadLinksByResourceTypeAndResourceId(resourceType: String, resourceId: String): List<String>

}