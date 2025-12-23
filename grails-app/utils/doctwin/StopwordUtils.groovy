package doctwin

class StopwordUtils {

    private static final Set<String> PT = [
            "a", "o", "e", "de", "do", "da", "que", "para", "com", "no", "na",
            "por", "em", "um", "uma", "os", "as", "se", "ou", "ao", "aos",
            "como", "mais", "mas", "foi", "ser", "tem", "há", "já", "não"
    ] as Set

    private static final Set<String> EN = [
            "the", "and", "of", "to", "for", "in", "on", "with", "is", "are",
            "was", "were", "be", "by", "that", "this", "it", "as", "from",
            "or", "an", "at", "which", "but", "not"
    ] as Set

    public static Boolean isStopword(String token) {
        return PT.contains(token) || EN.contains(token)
    }
}
