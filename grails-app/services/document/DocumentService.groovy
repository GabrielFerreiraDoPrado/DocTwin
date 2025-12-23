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
            InputStream is = pdfFile.inputStream
            PDDocument document = PDDocument.load(is)
        ) {
            String text = extractText(document)
            return normalize(text)
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
