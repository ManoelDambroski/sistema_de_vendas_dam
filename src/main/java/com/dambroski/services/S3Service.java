package com.dambroski.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	@Value("${s3.bucket}")
	private String bucketName;
	
	@Autowired
	private AmazonS3 s3Client;
	
	
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);
	
	
	
	public void uploadFile(String localFilePath) {
		try {
			File file = new File(localFilePath);
			s3Client.putObject(new PutObjectRequest(bucketName, "teste.jpg", file));			
		} 
		catch (AmazonServiceException e) {
			LOG.info(e.getErrorMessage());
		}
		catch(AmazonClientException e) {
			LOG.info(e.getMessage());
		}
	}
	
	
	
	
}
