package com.example.RAG.Repository;

import com.example.RAG.model.ProductDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDocumentRepository extends JpaRepository<ProductDocument,Long> {
    //CRUD operations
}
