package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.Upload;
import com.akandouch.invoicec.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadRepository uploadRepository;

    @Override
    public Upload saveOrUpdate(Upload upload) {
        return uploadRepository.save(upload);
    }

    @Override
    public Upload save(byte[] input, String contentType) {
        Upload upl = Upload.builder()
                .contentType(contentType)
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
