package com.borba.storage.controller;

import com.borba.storage.service.S3Service;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    private final S3Service s3Service;

    public FileController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam MultipartFile file) throws IOException {
        s3Service.uploadFile(file.getOriginalFilename(), file.getBytes());
        return ResponseEntity.ok("Upload feito com sucesso");
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable String filename){
        byte[] content = s3Service.downloadFile(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(content);
    }

    @GetMapping
    public List<String> listFiles(){
        return s3Service.listFiles();
    }

    @GetMapping("/teste")
    public String hello(){
        return "Hello word automatizado";
    }

    @DeleteMapping("/{filename}")
    public ResponseEntity<String> delete(@PathVariable String filename){
        s3Service.deleteFiles(filename);
        return ResponseEntity.ok("Arquivo deletado com sucesso");
    }


}
