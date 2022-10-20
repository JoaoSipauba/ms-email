package com.ms.msemail.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "file")
public class FileModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String fileId;
    private String filename;
    private byte[] file;
}
