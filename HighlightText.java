import javax.swing.*;
import javax.swing.text.*;
import java.awt.Color;

public class HighlightText {

    public void highlightText(String typedText, String referenceText, JTextPane typingArea) {
        try {
            StyledDocument doc = typingArea.getStyledDocument();
            removeHighlight(typingArea);
            char[] referenceChars = referenceText.toCharArray();
            char[] typedChars = typedText.toCharArray();
            for (int i = 0; i < typedChars.length; i++) {
                Style style = doc.addStyle("Style", null);
                if (i < referenceChars.length && typedChars[i] == referenceChars[i]) {
                    StyleConstants.setForeground(style, Color.GREEN);
                } else {
                    StyleConstants.setForeground(style, Color.RED);
                }
                doc.setCharacterAttributes(i, 1, style, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeHighlight(JTextPane typingArea) {
        try {
            StyledDocument doc = typingArea.getStyledDocument();
            Style normalStyle = doc.addStyle("NormalStyle", null);
            StyleConstants.setForeground(normalStyle, Color.BLACK);
            doc.setCharacterAttributes(0, doc.getLength(), normalStyle, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
