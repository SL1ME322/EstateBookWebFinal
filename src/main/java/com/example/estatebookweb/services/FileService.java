package com.example.estatebookweb.services;

import com.example.estatebookweb.models.FileModel;
import com.example.estatebookweb.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    public String storeFile(MultipartFile file) throws IOException {
        FileModel fileModel = FileModel
                .builder()
                .name(file.getName())
                .type(file.getContentType())
                .content(file.getBytes())
                .build();
        fileRepository.save(fileModel);
        if (fileModel.getId() != null){
            return "Файл был успешно загружен в базу данных";
        }

        return null;
    }

    public byte[] getFile(Long id) {
        return fileRepository.findById(id).map(FileModel::getContent).orElse(null);
    }
}
