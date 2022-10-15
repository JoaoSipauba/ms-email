package com.ms.msemail.models;

import com.ms.msemail.enums.StatusEmail;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "email")
public class EmailModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String emailId;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private List<MultipartFile> attachment;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;
}
