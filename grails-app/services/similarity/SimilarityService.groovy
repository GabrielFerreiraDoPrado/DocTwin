package similarity

import org.springframework.web.multipart.MultipartFile

class SimilarityService {

    DocumentService documentService

    public Double cosineSimilarity(MultipartFile pdf1, MultipartFile pdf2) {
        String text1 = documentService.extractNormalizedTextFromPDF(pdf1)
        String text2 = documentService.extractNormalizedTextFromPDF(pdf2)

        if (!text1 || !text2) {
            return 0.0
        }

        Map<String, Integer> vector1 = termFrequency(text1)
        Map<String, Integer> vector2 = termFrequency(text2)

        return cosineSimilarity(vector1, vector2)
    }

    private Map<String, Integer> termFrequency(String text) {
        Map<String, Integer> frequencyInfo = [:]

        text.split(" ").each { token ->
            if (token) {
                frequencyInfo[token] = (frequencyInfo[token] ?: 0) + 1
            }
        }

        return frequencyInfo
    }

    private Double cosineSimilarity(Map<String, Integer> v1, Map<String, Integer> v2) {
        Set<String> allTermList = v1.keySet() + v2.keySet()

        Double dotProduct = 0.0
        Double normV1 = 0.0
        Double normV2 = 0.0

        allTermList.each { term ->
            Integer x = v1[term] ?: 0
            Integer y = v2[term] ?: 0

            dotProduct += x * y
            normV1 += x * x
            normV2 += y * y
        }

        if (normV1 == 0 || normV2 == 0) {
            return 0.0
        }

        return dotProduct / (Math.sqrt(normV1) * Math.sqrt(normV2))
    }
}