import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontSelection {

    public void setBanglaFont(JTextPane nonEditableArea, JTextPane typingArea) {
        try {
            Font kalpurushFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\SPL-1 (final)\\Kalpurush.ttf"));
            kalpurushFont = kalpurushFont.deriveFont(18f);
            nonEditableArea.setFont(kalpurushFont);
            typingArea.setFont(kalpurushFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void setEnglishFont(JTextPane nonEditableArea, JTextPane typingArea) {
        Font englishFont = new Font("C:\\SPL-1 (final)\\arialbi.ttf", Font.PLAIN, 18);
        nonEditableArea.setFont(englishFont);
        typingArea.setFont(englishFont);
    }
}
