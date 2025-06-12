package com.app.letrachica.infra.rest;

import com.app.letrachica.core.gateway.DocumentAnalysis;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Component
public class DocumentAnalysisFromGeminiRepository implements DocumentAnalysis {

    private static final String API_KEY = "";
    private static final String URL ="https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=";
    @Override
    public String getDocument(String contractText) {
        RestTemplate restTemplate = new RestTemplate();

        String prompt = getPrompt(contractText);

        Map<String, Object> textPart = Map.of("text", prompt);
        Map<String, Object> messagePart = Map.of("parts", List.of(textPart));
        Map<String, Object> requestBody = Map.of("contents", List.of(messagePart));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            System.out.println("Sending request to Gemini API with prompt: " + prompt);
            ResponseEntity<String> response = restTemplate.postForEntity(URL, request, String.class);
            System.out.println(response.getBody());
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw e;
        }
    }

    private String getPrompt(String contractText) {
        return "Analiza el siguiente contrato. Identifica y separa claramente:\n" +
                "\n" +
                "1. ✅ Puntos positivos (ventajas para el firmante).\n" +
                "2. ❌ Puntos negativos o cláusulas que pueden ser desventajosas.\n" +
                "3. ⚠️ Riesgos o cláusulas ambiguas que podrían generar conflicto.\n" +
                "4. 📝 Recomendaciones para revisar antes de firmar.\n" +
                "\n" +
                "Usa un lenguaje claro y sencillo para alguien que no es abogado.\n" +
                "\n" +
                "Texto del contrato: " + contractText;
    }
}
