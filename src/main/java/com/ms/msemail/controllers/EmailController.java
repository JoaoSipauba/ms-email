package com.ms.msemail.controllers;

import com.ms.msemail.models.EmailModel;
import com.ms.msemail.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping(value = "/sending-email", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EmailModel> sendingEmail(
            @RequestPart String ownerRef,
            @RequestPart String emailFrom,
            @RequestPart String emailTo,
            @RequestPart String subject,
            @RequestPart String text,
            @RequestPart(required = false) List<MultipartFile> files
    ){
        EmailModel emailModel = new EmailModel();
        emailModel.setOwnerRef(ownerRef);
        emailModel.setEmailFrom(emailFrom);
        emailModel.setEmailTo(emailTo);
        emailModel.setSubject(subject);
        emailModel.setText(text);

        emailService.sendEmail(emailModel, files);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }
}
