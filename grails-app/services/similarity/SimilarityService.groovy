package similarity

import doctwin.StopwordUtils
import document.DocumentService

import org.springframework.web.multipart.MultipartFile

class SimilarityService {

    DocumentService documentService

    public Double compare(MultipartFile file1, MultipartFile file2) {
        String text1 = documentService.extractNormalizedTextFromPDF(file1)
        String text2 = documentService.extractNormalizedTextFromPDF(file2)

        if (!text1 || !text2) {
            return 0.0
        }

        Map<String, Integer> vector1 = findTermFrequency(text1)
        Map<String, Integer> vector2 = findTermFrequency(text2)

        return cosineSimilarity(vector1, vector2)
    }

    private Map<String, Integer> findTermFrequency(String text) {
        Map<String, Integer> frequencyInfo = [:]

        text.split(" ").each { token ->
            if (token) {
                if (StopwordUtils.isStopword(token)) return

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

    private Double jaccardSimilarity(Set<String> tokens1, Set<String> tokens2) {
        if (!tokens1 || !tokens2) {
            return 0.0
        }

        Set<String> intersection = tokens1.intersect(tokens2)
        Set<String> union = tokens1.union(tokens2)

        if (union.isEmpty()) {
            return 0.0
        }

        return intersection.size() / (double) union.size()
    }
}