package com.thanhtai.localstack.service;

import com.thanhtai.localstack.api.model.PersonRequestModel;


public interface StorageService {
    String upload(PersonRequestModel personRequestModel);
}
