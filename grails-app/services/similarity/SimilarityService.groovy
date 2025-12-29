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

        Map<String, Integer> termFrequency1 = findTermFrequency(text1)
        Map<String, Integer> termFrequency2 = findTermFrequency(text2)

        Map<String, Double> termFrequencyIdf1 = computeTermFrequencyIdf(termFrequency1, termFrequency2)
        Map<String, Double> termFrequencyIdf2 = computeTermFrequencyIdf(termFrequency2, termFrequency1)

        Double cosine = cosineSimilarity(termFrequencyIdf1, termFrequencyIdf2)

        Set<String> tokens1 = termFrequency1.keySet()
        Set<String> tokens2 = termFrequency2.keySet()

        Double jaccard = jaccardSimilarity(tokens1, tokens2)

        return combinedSimilarity(cosine, jaccard)
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

    private Map<String, Double> computeTermFrequencyIdf(Map<String, Integer> currentTermFrequency, Map<String, Integer> otherTermFrequency) {
        Map<String, Double> termFrequencyIdf = [:]

        final Integer totalDocuments = 2

        currentTermFrequency.each { term, frequency ->
            Integer documentFrequency = 1

            if (otherTermFrequency.containsKey(term)) {
                documentFrequency += 1
            }

           Double idf = Math.log((totalDocuments + 1.0) / (documentFrequency + 1.0)) + 1.0

            termFrequencyIdf[term] = frequency * idf
        }

        return termFrequencyIdf
    }

    private Double cosineSimilarity(Map<String, Double> termFrequencyIdf1, Map<String, Double> termFrequencyIdf2) {
        Set<String> allTermList = termFrequencyIdf1.keySet() + termFrequencyIdf2.keySet()

        Double dotProduct = 0.0
        Double normV1 = 0.0
        Double normV2 = 0.0

        allTermList.each { term ->
            Double x = termFrequencyIdf1[term] ?: 0.0
            Double y = termFrequencyIdf2[term] ?: 0.0

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
        Set<String> union = tokens1 + tokens2

        if (union.isEmpty()) {
            return 0.0
        }

        return intersection.size() / (Double) union.size()
    }

    private Double combinedSimilarity(Double cosine, Double jaccard) {
        final Double alpha = 0.85

        return alpha * cosine + (1 - alpha) * jaccard
    }
}