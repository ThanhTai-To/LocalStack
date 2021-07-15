package com.thanhtai.localstack.controller;

import com.thanhtai.localstack.api.S3Api;
import com.thanhtai.localstack.api.model.ObjectSuccessResponse;
import com.thanhtai.localstack.api.model.PersonRequestModel;
import com.thanhtai.localstack.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StorageController implements S3Api {

    @Autowired
    private StorageService storageService;

    @Override
    public ResponseEntity<ObjectSuccessResponse> uploadFile(@Valid PersonRequestModel personRequestModel) {
        String data = storageService.upload(personRequestModel);
        ObjectSuccessResponse response = buildObjectSuccessResponse(data);
        return ResponseEntity.ok(response);
    }

    private ObjectSuccessResponse buildObjectSuccessResponse(String data) {
        ObjectSuccessResponse response = new ObjectSuccessResponse();
        response.setResponseCode(HttpStatus.OK.value());
        response.setMessage("Upload success data");
        response.setData(data);
        return  response;
    }
}
