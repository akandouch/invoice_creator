package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.Upload;

public interface UploadService {
    Upload saveOrUpdate(Upload upload);
    Upload save(byte[] input, String contentType, String fileName);
    Upload get(String id);
    Upload delete(String id);
    void deleteAll();
}
