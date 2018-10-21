package com.akandouch.invoicec.restcontroller;

import com.akandouch.invoicec.domain.Upload;
import com.akandouch.invoicec.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/upload")
@CrossOrigin("*")
@Slf4j
public class UploadRestController {

    @Autowired
    private UploadService uploadService;

    @GetMapping("/{id}")
    public ResponseEntity<Resource> get(@PathVariable("id") String id) {
        Upload upload = uploadService.get(id);
        if (upload == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(upload.getContentType()))
                .body(new ByteArrayResource(upload.getUpload()));
    }

    @PostMapping
    public Upload saveOrUpdate(@RequestBody Upload upload) {
        return uploadService.saveOrUpdate(upload.toBuilder().upload(upload.getNewUpload()).build());
    }

    @PostMapping("/delete/{id}")
    public Upload delete(@PathVariable("id") String id){
        return uploadService.delete(id);
    }



}
