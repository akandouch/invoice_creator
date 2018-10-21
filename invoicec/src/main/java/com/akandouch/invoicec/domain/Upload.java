package com.akandouch.invoicec.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Upload {
    private String id;

    @JsonIgnore
    private byte[] upload;

    @Transient
    private byte[] newUpload;

    private String fileName;
    private String contentType;
}
