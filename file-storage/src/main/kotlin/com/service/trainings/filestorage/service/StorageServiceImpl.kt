package com.service.trainings.filestorage.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.charset.StandardCharsets


@Service
class StorageServiceImpl(@Autowired val s3Client: AmazonS3) : StorageService {

    override fun getBucketList(): MutableList<Bucket>? = s3Client.listBuckets()

    override fun uploadObject(bucketName: String, filePath: String, file: MultipartFile) {
        if (!s3Client.doesBucketExistV2("/$bucketName")) {
            s3Client.createBucket(bucketName)
        }
        val meta = ObjectMetadata().apply {
            contentLength = file.size
            contentEncoding = StandardCharsets.UTF_8.name()
        }
        s3Client.putObject("/$bucketName", filePath, file.inputStream, meta)
    }

    override fun getAllByBucketName(bucketName: String): ObjectListing? = s3Client.listObjects("$bucketName/")

    override fun downloadByBucketNameAndFilePath(bucketName: String, filePath: String): S3Object? {
        return s3Client.getObject("/$bucketName", filePath)
    }
}