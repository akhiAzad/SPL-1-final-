public class Speed {
    private int wordCount;

    public void updateTypingStatistics(String typedText, String referenceText) {
        String[] words = typedText.split("\\s+");
        wordCount = words.length;
    }

    public double calculateWPM() {
        return (wordCount / 2.0); // Assuming a 2-minute typing test
    }
}
