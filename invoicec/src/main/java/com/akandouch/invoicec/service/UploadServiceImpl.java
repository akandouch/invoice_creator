package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.Upload;
import com.akandouch.invoicec.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadRepository uploadRepository;

    @Override
    public Upload saveOrUpdate(Upload upload) {
        return uploadRepository.save(upload);
    }

    @Override
    public Upload save(byte[] input, String contentType, String fileName) {
        Upload upl = Upload.builder()
                .id(UUID.randomUUID().toString())
                .contentType(contentType)
                .fileName(fileName)
                .upload(input)
                .build();
        return uploadRepository.save(upl);
    }

    @Override
    public Upload get(String id) {
        return uploadRepository.findById(id).orElse(null);
    }

    @Override
    public Upload delete(String id) {
        Upload up = get(id);
        uploadRepository.delete(up);
        return up;
    }

    @Override
    public void deleteAll() {
        uploadRepository.deleteAll();
    }
}
