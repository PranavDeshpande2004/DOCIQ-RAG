



# 📄 DOCIQ-RAG 

A complete **Retrieval-Augmented Generation (RAG)** system that allows users to upload PDFs, extracts text, chunks it, embeds it using OpenAI models, stores embeddings in PGVector, and answers user questions using similarity search + LLM.

---

## 🚀 Features

* Upload PDF files
* Extract text using **Apache PDFBox**
* Store extracted text in **PostgreSQL**
* Chunk documents using **TokenTextSplitter**
* Generate embeddings using **text-embedding-3-small**
* Store vectors in **PGVector**
* Similarity search on user queries
* Generate LLM answers using **GPT**
* Clean, modular Spring Boot architecture

---

## 🧠 Architecture (High-Level)

```
User → Upload PDF → PDFBox text extraction
     → Save text in PostgreSQL
     → Chunk + Embed using Spring AI
     → Store vectors in PGVector
User → Ask Query → Embed Query → Vector Search
     → Retrieve top-k chunks → LLM (GPT)
     → Final Answer
```

---

## 🛠 Tech Stack

* **Spring Boot**
* **Spring AI** (Embeddings + LLM + RAG Advisor)
* **PostgreSQL + PGVector**
* **Apache PDFBox** (PDF text extraction)
* **OpenAI Models**



---

## 📤 API Endpoints

### Upload PDF

```
POST /api/pdf/upload
form-data: file=<your.pdf>
```

### Ask Question

```
GET /api/rag/query?question=...
```

---

## ⚙️ Configuration (`application.properties`)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ragdb
spring.datasource.username=postgres
spring.datasource.password=root

spring.ai.openai.api-key=YOUR_OPENAI_KEY
spring.ai.openai.chat.model=gpt-4o-mini
spring.ai.openai.embedding.model=text-embedding-3-small

spring.ai.vectorstore.pgvector.table-name=rag_vectors
spring.ai.vectorstore.pgvector.dimensions=1536
```

Enable pgvector:

```sql
CREATE EXTENSION IF NOT EXISTS vector;
```

---

## 📁 Main Components

* `PdfService` → Extract text from PDF
* `UploadService` → Save text to DB
* `JpaDocumentLoader` → Load documents for ingestion
* `VectorIngestionService` → Chunk + embed + store in PGVector
* `RagQueryService` → RAG retrieval + LLM generation
* `PdfController` / `RagController` → REST API

---


