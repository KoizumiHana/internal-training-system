package com.service.trainings.training.integration

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@FeignClient(name = "storage")
interface FileStorageFeignClient {

    @PostMapping("/storage/{resourceType}/{resourceId}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadFile(
        @RequestPart multipartFile: MultipartFile,
        @PathVariable resourceType: String,
        @PathVariable resourceId: String
    )

    @GetMapping("/storage/{resourceType}/{resourceId}")
    fun getAllLinksRelatedToResource(
        @PathVariable resourceType: String,
        @PathVariable resourceId: String
    ): List<String>

    @DeleteMapping("/storage/")
    fun deleteFileByUUID(@RequestBody uuid: String)

    @PostMapping("/storage/{resourceType}")
    fun getAllLinksRelatedToResources(
        @PathVariable resourceType: String,
        @RequestBody resourceIds: List<String>
    ): Map<String, List<String>>
}