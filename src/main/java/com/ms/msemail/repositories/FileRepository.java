package com.ms.msemail.repositories;

import com.ms.msemail.models.FileModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<FileModel, String> {
}
