package com.app.letrachica.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    // TODO: Implement actual document upload logic
    // Mock endpoint: Get a document to analyze
    @PostMapping("/analyze")
    public Map<String, Object> analyzeDocument(@RequestBody Map<String, Object> document) {
        // Mock analysis logic
        return Map.of(
            "status", "received",
            "message", "Document received for analysis",
            "document_id", "doc-001" // Return the original document for simplicity
        );
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
