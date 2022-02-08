package com.service.trainings.filestorage.controller

import com.service.trainings.filestorage.service.FileStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.nio.charset.StandardCharsets

@RestController
@RequestMapping("/storage")
class FileController(@Autowired val fileStorageService: FileStorageService) {

    @GetMapping("/{resourceType}/{resourceId}")
    fun getAllLinksRelatedToResource(
        @PathVariable resourceType: String,
        @PathVariable resourceId: String
    ): List<String> = fileStorageService.getListOfLinksRelatedToResource(resourceType, resourceId)

    @PostMapping("/{resourceType}")
    fun getAllLinksRelatedToResources(
        @PathVariable resourceType: String,
        @RequestBody resourceIds: List<String>
    ): Map<String, List<String>> = resourceIds.associateWith {
        fileStorageService.getListOfLinksRelatedToResource(resourceType, it)
    }

    @GetMapping("/download/{uuid}")
    fun downloadFileByUUID(@PathVariable uuid: String): ResponseEntity<ByteArrayResource> {
        val fileByUUID = fileStorageService.downloadFileByUUID(uuid)
        val response = ByteArrayResource(fileByUUID!!.objectContent.readAllBytes())
        val header = HttpHeaders()

        val contentDisposition: ContentDisposition = ContentDisposition.builder("attachment")
            .filename(fileByUUID.key, StandardCharsets.UTF_8).build()
        header.contentDisposition = contentDisposition
        header.add("Cache-Control", "no-cache, no-store, must-revalidate")
        header.add("Pragma", "no-cache")
        header.add("Expires", "0")
        return ResponseEntity.ok()
            .contentLength(response.contentLength())
            .contentType(MediaType(MediaType.APPLICATION_OCTET_STREAM, StandardCharsets.UTF_8))
            .headers(header)
            .body(response)
    }

    @PostMapping("/{resourceType}/{resourceId}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadFile(
        @RequestBody multipartFile: MultipartFile,
        @PathVariable resourceType: String,
        @PathVariable resourceId: String
    ) = fileStorageService.saveFile(multipartFile, resourceType, resourceId)

    @DeleteMapping("/")
    fun deleteFileByUUID(@RequestBody uuid: String) = fileStorageService.deleteFileByUUID(uuid)

}