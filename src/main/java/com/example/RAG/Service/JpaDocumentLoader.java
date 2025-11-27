package com.example.RAG.Service;

import com.example.RAG.Repository.ProductDocumentRepository;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class JpaDocumentLoader {
    private  final ProductDocumentRepository productDocumentRepository;

    @Autowired
    public  JpaDocumentLoader(ProductDocumentRepository productDocumentRepository){
        this.productDocumentRepository=productDocumentRepository;
    }

    public List<Document>loadAllDocuments(){
        return productDocumentRepository.findAll().stream()
                .map(entity->{
                    // Create a Spring AI Document from the JPA entity's content
                    Document Doc=new Document(entity.getContent());
                    // Add metadata for better retrieval/context (optional but recommended)
                    Doc.getMetadata().put("document_id",entity.getId());
                    Doc.getMetadata().put("title",entity.getTitle());
                    return Doc;
                }).collect(Collectors.toList());
    }
}

//retrieve data from a traditional database
//(using JPA/Spring Data) and transform it into the Spring AI format (Document),