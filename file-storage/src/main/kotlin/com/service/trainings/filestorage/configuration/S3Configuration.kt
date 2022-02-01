package com.service.trainings.filestorage.configuration

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class S3Configuration {

    @Bean
    @Primary
    fun localS3Client(
        @Value("\${cloud.aws.s3.endpoint}") serviceEndpoint: String,
        @Value("\${cloud.aws.s3.region}") signingRegion: String,
        @Value("\${cloud.aws.s3.pathStyleAccessEnabled}") pathStyleAccessEnabled: Boolean
    ): AmazonS3 =
        AmazonS3Client.builder()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(serviceEndpoint, signingRegion))
            .withPathStyleAccessEnabled(pathStyleAccessEnabled)
            .build()
}