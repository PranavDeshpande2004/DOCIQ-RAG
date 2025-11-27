package com.example.RAG.Config; // Or a relevant 'runner' package

import com.example.RAG.Service.VectorIngestionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component // Makes this class a Spring bean
public class DataInitializer implements CommandLineRunner {

    private final VectorIngestionService ingestionService;

    // Inject the service you created in the configuration
    public DataInitializer(VectorIngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    // This method is called automatically on startup
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting data ingestion into Vector Store...");
        try {
            ingestionService.ingestAllJpaDocuments();
            System.out.println("Data ingestion successful!");
        } catch (Exception e) {
            System.err.println("Data ingestion failed: " + e.getMessage());
            // Depending on your setup, you might want to throw the exception
            // or just log it if the application can run without the RAG data.
        }
    }
}