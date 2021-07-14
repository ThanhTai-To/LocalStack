package com.thanhtai.localstack.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanhtai.localstack.api.model.PersonRequestModel;
import com.thanhtai.localstack.config.AwsS3ClientConfig;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;


@Service
public class StorageServiceImpl implements StorageService {

//    private final AmazonS3 amazonS3;
//    private final String bucketName;
//    public StorageServiceImpl(AmazonS3 amazonS3, @Value("${aws.s3.bucket-name}") String bucketName) {
//        this.amazonS3 = amazonS3;
//        this.bucketName = bucketName;
//        initializeBucket();
//    }

    @Autowired
    private AwsS3ClientConfig awsS3Client;



    @Override
    public String upload(PersonRequestModel personRequestModel) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", personRequestModel.getFirstName());
        jsonObject.put("lastName", personRequestModel.getLastName());
        jsonObject.put("phone", personRequestModel.getPhone());
        try {
            File file = File.createTempFile("aws-java-sdk-", ".txt");
            file.deleteOnExit();

            Writer writer = new OutputStreamWriter(new FileOutputStream(file));
            writer.write(jsonObject.toJSONString());
            writer.close();
            System.out.println("Uploading a new object to " + awsS3Client.getAwsS3Bucket() + " from a file" + file + "\n with file name " + file.getName());

            awsS3Client.amazonS3().putObject(new PutObjectRequest(awsS3Client.getAwsS3Bucket(), file.getName(), file));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
