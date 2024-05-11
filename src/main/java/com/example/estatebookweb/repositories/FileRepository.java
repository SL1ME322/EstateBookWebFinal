package com.example.estatebookweb.repositories;

import com.example.estatebookweb.models.EstateModel;
import com.example.estatebookweb.models.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository  extends JpaRepository<FileModel, Long> {
}
