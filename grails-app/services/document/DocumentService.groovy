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
            String rawText = extractText(document)

            return normalize(rawText)
        }
    }

    public Boolean isPdf(MultipartFile file) {
        byte[] header = new byte[5]
        InputStream inputStream = file.inputStream

        Integer bytesRead = inputStream.read(header)
        inputStream.close()

        if (bytesRead < 5) {
            return false
        }

        return header[0] == 0x25 &&
               header[1] == 0x50 &&
               header[2] == 0x44 &&
               header[3] == 0x46 &&
               header[4] == 0x2D
    }

    private String extractText(PDDocument document) {
        PDFTextStripper textStripper = new PDFTextStripper()
        textStripper.setSortByPosition(true)

        return textStripper.getText(document)
    }

    private String normalize(String text) {
        return text
                .toLowerCase()
                .replaceAll("[^\\p{L}0-9\\s]", " ")
                .replaceAll("\\s+", " ")
                .trim()
    }
}
