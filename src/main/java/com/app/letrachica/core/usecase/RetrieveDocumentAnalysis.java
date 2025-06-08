package com.app.letrachica.core.usecase;

import com.app.letrachica.core.gateway.DocumentAnalysis;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RetrieveDocumentAnalysis {

    private final DocumentAnalysis documentAnalysis;

    public String getDocumentAnalysis(MultipartFile file) throws IOException {
        String contractText = extraerTextoDePdf(file);
        return documentAnalysis.getDocument(contractText);
    }

    private String extraerTextoDePdf(MultipartFile archivo) throws IOException {
        PDDocument document = PDDocument.load(archivo.getInputStream());
        PDFTextStripper stripper = new PDFTextStripper();
        String texto = stripper.getText(document);
        document.close();
        return texto;
    }
}
