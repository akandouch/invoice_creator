package com.akandouch.invoicec.repository;

import com.akandouch.invoicec.domain.Upload;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends MongoRepository<Upload, String> {

}
