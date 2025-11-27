package com.example.RAG.Config;

import com.example.RAG.Repository.ProductDocumentRepository;
import com.example.RAG.Service.JpaDocumentLoader;
import com.example.RAG.Service.RagQueryService;
import com.example.RAG.Service.VectorIngestionService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.DocumentTransformer;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IngestionConfig {

    // 1. Document Splitter Bean (You already wrote this one!)
    @Bean
    public DocumentTransformer tokenTextSplitter(){
        // Using DocumentTransformer interface type for wider compatibility
        return new TokenTextSplitter(800, 100, 1, 100, true);
    }

    // 2. JpaDocumentLoader Bean
    @Bean
    public JpaDocumentLoader jpaDocumentLoader(ProductDocumentRepository productDocumentRepository) {
        return new JpaDocumentLoader(productDocumentRepository);
    }

    // 3. VectorIngestionService Bean
    @Bean
    public VectorIngestionService vectorIngestionService(
            JpaDocumentLoader documentLoader,
            DocumentTransformer tokenTextSplitter, // The bean defined above
            VectorStore vectorStore) { // Auto-configured by Spring AI

        return new VectorIngestionService(documentLoader, tokenTextSplitter, vectorStore);
    }

    // 4. RagQueryService Bean
    @Bean
    public RagQueryService ragQueryService(
            ChatClient.Builder chatClientBuilder, // Auto-configured by Spring AI
            VectorStore vectorStore) {             // Auto-configured by Spring AI

        return new RagQueryService(chatClientBuilder, vectorStore);
    }
}