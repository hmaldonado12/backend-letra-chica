package com.app.letrachica.controller.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.app.letrachica.controller.contract.DocumentResponse;
import com.app.letrachica.controller.contract.SaveDocumentRequest;
import com.app.letrachica.core.domain.Document;
import com.app.letrachica.core.gateway.DocumentRepository;
import com.app.letrachica.core.usecase.RetrieveDocumentAnalysis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/categories/{categoryId}/documents")
@CrossOrigin(origins = "http://localhost:8080")
public class DocumentController {

    private final RetrieveDocumentAnalysis retrieveDocumentAnalysis;
    private final DocumentRepository documentRepository;

    public DocumentController(RetrieveDocumentAnalysis retrieveDocumentAnalysis, DocumentRepository documentRepository) {
        this.retrieveDocumentAnalysis = retrieveDocumentAnalysis;
        this.documentRepository = documentRepository;
    }

    @PostMapping
    public ResponseEntity<DocumentResponse> saveDocument(@RequestBody SaveDocumentRequest request) {
        Document document = new Document(
                request.getTitle(),
                request.getSummary(),
                request.getUserId(),
                request.getCategoryId(),
                request.getStatus()
        );
        document.setStatus("analyzed");
        documentRepository.save(document);
        return ResponseEntity.ok(new DocumentResponse(
                document.getId(),
                document.getTitle(),
                document.getStatus(),
                document.getCategoryId(),
                document.getCreatedAt(),
                document.getSummary()
        ));
    }

    @PostMapping("/analyze")
    public String analyzeDocument(MultipartFile file) throws IOException {
        return retrieveDocumentAnalysis.getDocumentAnalysis(file);
    }

    @GetMapping
    public Map<String, Object> getDocuments() {
        List<Document> documents = documentRepository.findAll();
        List<DocumentResponse> response = documents.stream()
                .map(doc -> new DocumentResponse(
                    doc.getId(), 
                    doc.getTitle(), 
                    doc.getStatus(), 
                    doc.getCategoryId(), 
                    doc.getCreatedAt(), 
                    doc.getSummary()
                ))
                .toList();
        return Map.of("documents", response);        
        // return Map.of(
        //         "documents", List.of(
        //                 Map.of("id", "doc-001", "title", "Contrato de Prueba", "status", "analyzed"),
        //                 Map.of("id", "doc-002", "title", "Factura", "status", "pending")
        //         )
        // );
    }

    @GetMapping("/{id}")
    public DocumentResponse getDocumentById(@PathVariable String id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        return new DocumentResponse(
                document.getId(),
                document.getTitle(),
                document.getStatus(),
                document.getCategoryId(),
                document.getCreatedAt(),
                document.getSummary()
        );
    }

    @PutMapping("/{id}/analyze")
    public ResponseEntity<DocumentResponse> reanalyzeDocument(@PathVariable String id, MultipartFile file) throws IOException {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        String newSummary = retrieveDocumentAnalysis.getDocumentAnalysis(file);
        document.setSummary(newSummary);
        document.setStatus("reanalyzed");
        documentRepository.save(document);
        return ResponseEntity.ok(new DocumentResponse(
                document.getId(),
                document.getTitle(),
                document.getStatus(),
                document.getCategoryId(),
                document.getCreatedAt(),
                document.getSummary()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable String id) {
        documentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
