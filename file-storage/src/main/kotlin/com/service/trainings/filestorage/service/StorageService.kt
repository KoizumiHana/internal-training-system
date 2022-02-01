package com.service.trainings.filestorage.service

import com.amazonaws.services.s3.model.Bucket
import com.amazonaws.services.s3.model.ObjectListing
import com.amazonaws.services.s3.model.S3Object
import org.springframework.web.multipart.MultipartFile

interface StorageService {
    fun getBucketList(): MutableList<Bucket>?
    fun getAllByBucketName(bucketName: String): ObjectListing?
    fun downloadByBucketNameAndFilePath(bucketName: String, filePath: String): S3Object?
    fun uploadObject(bucketName: String, filePath: String, file: MultipartFile)
}
