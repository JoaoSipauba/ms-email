package com.ms.msemail.repositories;

import com.ms.msemail.models.EmailModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepository extends MongoRepository<EmailModel, String> {

}
