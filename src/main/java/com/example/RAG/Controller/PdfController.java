package com.example.RAG.Controller;

import com.example.RAG.Service.UploadService;
import com.example.RAG.Service.VectorIngestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private final UploadService uploadService;
    private final VectorIngestionService ingestionService;

    public PdfController(UploadService uploadService, VectorIngestionService ingestionService) {
        this.uploadService = uploadService;
        this.ingestionService = ingestionService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file) throws Exception {
        String msg = uploadService.uploadPdf(file);

        ingestionService.ingestAllJpaDocuments(); // push to PGVector

        return ResponseEntity.ok(msg + " | Vector Ingestion Completed.");
    }
}
