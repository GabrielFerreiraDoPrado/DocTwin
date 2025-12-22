package doctwin.similarity

import grails.converters.JSON

import org.springframework.web.multipart.MultipartFile

class SimilarityController {

    static allowedMethods = [compare: "POST"]

    def compare() {
        MultipartFile file1 = request.getFile("file1")
        MultipartFile file2 = request.getFile("file2")

        if (file1.empty || file2.empty) {
            response.status = 400
            render([
                    error: "É necessário enviar dois arquivos para comparação."
            ] as JSON)
            return
        }

        String text1 = file1.inputStream.text
        String text2 = file1.inputStream.text

        Double similarityScore = 0.0

        render([
                document1: file1.originalFilename,
                document2: file2.originalFilename,
                similarityScore: similarityScore
        ] as JSON)
    }
}
