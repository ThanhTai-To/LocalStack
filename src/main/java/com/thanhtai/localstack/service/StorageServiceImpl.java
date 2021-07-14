package com.thanhtai.localstack.service;

import com.amazonaws.services.s3.model.PutObjectRequest;
import com.thanhtai.localstack.api.model.PersonRequestModel;
import com.thanhtai.localstack.config.AwsS3ClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;


@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

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
            log.info("Uploading a new object to " + awsS3Client.getAwsS3Bucket() + " from a file" + file + "\n with file name " + file.getName());

            awsS3Client.amazonS3().putObject(new PutObjectRequest(awsS3Client.getAwsS3Bucket(), file.getName(), file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
