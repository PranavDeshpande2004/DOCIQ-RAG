package com.example.RAG.Service;

import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentTransformer;
import org.springframework.ai.vectorstore.VectorStore;

import java.util.List;

public class VectorIngestionService {
    private final JpaDocumentLoader documentLoader;
    private final DocumentTransformer splitter;
    private final VectorStore vectorStore;

    // Injected components: Our loader, the splitter bean, and the auto-configured VectorStore
    public VectorIngestionService(JpaDocumentLoader documentLoader,
                                  DocumentTransformer splitter,
                                  VectorStore vectorStore) {
        this.documentLoader = documentLoader;
        this.splitter = splitter;
        this.vectorStore = vectorStore;
    }

    public void ingestAllJpaDocuments() {
        // 1. LOAD: Get Documents from the JPA source
        List<Document> documents = documentLoader.loadAllDocuments();

        // 2. TRANSFORM: Chunk the documents using the splitter
        List<Document> chunks = splitter.transform(documents);

        // 3. STORE: Send the chunks to the Vector Store.
        //    The VectorStore automatically calls the EmbeddingClient
        //    to convert each chunk into a vector before saving.
        vectorStore.add(chunks);

        System.out.println("Ingestion complete. " + chunks.size() + " chunks saved to Vector Store.");
    }
}


