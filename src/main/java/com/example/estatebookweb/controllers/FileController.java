package com.example.estatebookweb.controllers;

import com.example.estatebookweb.models.EstateModel;
import com.example.estatebookweb.repositories.EstateRepository;
import com.example.estatebookweb.repositories.FileRepository;
import com.example.estatebookweb.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;
    private final FileRepository fileRepository;
    private final EstateRepository estateRepository;

    public FileController(FileService fileService, FileRepository fileRepository, EstateRepository estateRepository) {
        this.fileService = fileService;
        this.fileRepository = fileRepository;

        this.estateRepository = estateRepository;
    }

//    @GetMapping("/upload")
//    public String uploadForm() {
//        return "html/upload";
//    }
//
//
//
//
//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        fileService.storeFile(file);
//        return "redirect:/upload";
//    }
//
//    @GetMapping("/file/{id}")
//    public void getFile(@PathVariable Long id, Model model , HttpServletResponse response) throws IOException {
//        byte[] content = fileService.getFile(id);
//        FileModel file = fileRepository.findById(id).orElse(null);
//
//        if (file != null && file.getType() != null) {
//            response.setContentType(file.getType());
//        } else {
//            response.setContentType("application/octet-stream");
//        }
//        model.addAttribute("id", id);
//        model.addAttribute("content", content);
//        response.getOutputStream().write(content);
//        response.getOutputStream().flush();
//    }


    @GetMapping("/{id}")
    public ResponseEntity<Set<String>> getFilesById(@PathVariable Long id){
        Optional<EstateModel> estateModel = estateRepository.findEstateModelById(id);
        if (estateModel.isPresent()){
            Set<String > files = estateModel.get().getFiles();
            return ResponseEntity.ok(files);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("getThreeD/{id}")
    public ResponseEntity<Set<String>> getThreeDById(@PathVariable Long id){
        Optional<EstateModel> estateModel = estateRepository.findEstateModelById(id);
        if (estateModel.isPresent()){
            Set<String > files = estateModel.get().getThreeDModel();
            return ResponseEntity.ok(files);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
