package com.example.RAG.Service;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;


public class RagQueryService {
    private final ChatClient chatClient;

    public RagQueryService(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        // 1. Create the RAG Advisor
        QuestionAnswerAdvisor advisor = QuestionAnswerAdvisor.builder(vectorStore).build();

        // 2. Build the ChatClient with the Advisor attached
        this.chatClient = chatClientBuilder
                .defaultAdvisors(advisor) // Attach the RAG advisor for all calls
                .build();
    }

    public String query(String question) {
        // The Advisor does the heavy lifting:
        // 1. Takes the 'question'
        // 2. Uses the VectorStore to find relevant chunks (Retrieval)
        // 3. Modifies the prompt to include the context (Augmentation)
        // 4. Sends the full prompt to the LLM (Generation)

        return this.chatClient.prompt()
                .user(question) // Pass the user's question
                .call()         // Execute the call
                .content();     // Get the final text response
    }
}
