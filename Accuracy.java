public class Accuracy {
    private int correctChars, totalTypedChars;

    public void updateTypingStatistics(String typedText, String referenceText) {
        totalTypedChars = typedText.length();
        correctChars = 0;
        char[] referenceChars = referenceText.toCharArray();
        char[] typedChars = typedText.toCharArray();
        for (int i = 0; i < typedChars.length; i++) {
            if (i < referenceChars.length && typedChars[i] == referenceChars[i]) {
                correctChars++;
            }
        }
    }

    public double calculateAccuracy() {
        return (double) correctChars / totalTypedChars * 100;
    }
}
