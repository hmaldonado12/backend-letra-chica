package com.app.letrachica.controller.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.app.letrachica.core.usecase.RetrieveDocumentAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
@CrossOrigin(origins = "http://localhost:8080")
public class DocumentController {

    private final RetrieveDocumentAnalysis retrieveDocumentAnalysis;

    public DocumentController(RetrieveDocumentAnalysis retrieveDocumentAnalysis) {
        this.retrieveDocumentAnalysis = retrieveDocumentAnalysis;
    }


    @PostMapping("/analyze")
    public String analyzeDocument(MultipartFile file) throws IOException {
        return retrieveDocumentAnalysis.getDocumentAnalysis(file);
    }

    // TODO: Implement actual document analysis logic
    // Mock endpoint: Get documents list
    @GetMapping
    public Map<String, Object> getDocuments() {
        // Mock data, replace with actual document retrieval logic
        return Map.of(
                "documents", List.of(
                        Map.of("id", "doc-001", "title", "Contrato de Prueba", "status", "analyzed"),
                        Map.of("id", "doc-002", "title", "Factura", "status", "pending")
                )
        );
    }
}
