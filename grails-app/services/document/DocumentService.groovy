package document

import grails.gorm.transactions.Transactional

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.springframework.web.multipart.MultipartFile

@Transactional
class DocumentService {

    public String extractNormalizedTextFromPDF(MultipartFile pdfFile) {
        if (!pdfFile || pdfFile.empty) {
            return ""
        }

        try (
            PDDocument document = PDDocument.load(pdfFile.inputStream)
        ) {
            return extractText(document)
        }
    }

    private String extractText(PDDocument document) {
        PDFTextStripper textStripper = new PDFTextStripper()
        textStripper.setSortByPosition(true)

        return textStripper.getText(document)
    }

    private String normalize(String text) {
        return text
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s]", " ")
                .replaceAll("\\s+", " ")
                .trim()
    }
}
