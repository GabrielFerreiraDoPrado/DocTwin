package doctwin.similarity

import grails.converters.JSON

import org.springframework.web.multipart.MultipartFile

import similarity.SimilarityService

class SimilarityController {

    static allowedMethods = [compare: "POST"]

    SimilarityService similarityService

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

        Double similarityScore = similarityService.compare(file1, file2)
        println("índice: ${similarityScore}")

        render(
            view: "result",
            model: [
                document1: file1.originalFilename,
                document2: file2.originalFilename,
                similarityScore: similarityScore
            ]
        )
    }
}
