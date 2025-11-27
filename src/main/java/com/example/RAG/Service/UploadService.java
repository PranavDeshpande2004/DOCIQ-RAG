package com.example.RAG.Service;

import com.example.RAG.Repository.ProductDocumentRepository;
import com.example.RAG.model.ProductDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

    private final PdfService pdfService;
    private final ProductDocumentRepository repo;

    public UploadService(PdfService pdfService, ProductDocumentRepository repo) {
        this.pdfService = pdfService;
        this.repo = repo;
    }

    public String uploadPdf(MultipartFile file) throws Exception {
        String extractedText = pdfService.extractText(file);

        ProductDocument doc = new ProductDocument();
        doc.setTitle(file.getOriginalFilename());
        doc.setContent(extractedText);

        repo.save(doc);

        return "PDF Uploaded & Text Saved in DB. Document ID = " + doc.getId();
    }
}
