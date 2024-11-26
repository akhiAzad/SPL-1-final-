import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class TypingInterface extends JFrame implements ActionListener, KeyListener {
    private JButton banglaButton, englishButton, endButton;
    private JLabel banglaInstructionLabel, englishInstructionLabel, timerLabel;
    private javax.swing.Timer countdownTimer;
    private int timeLeft;
    private JTextPane typingArea;
    private String referenceText = "";
    private Speed speedCounter;
    private Accuracy accuracyCounter;
    private HighlightText highlightText;
    private FontSelection fontSelection;

    //create instruction panel 
    public TypingInterface() {
        setTitle("SpeedTest");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel instructionPanel = new JPanel(new GridLayout(1, 2));

        // Bangla Instruction Label
        banglaInstructionLabel = new JLabel("<html><h3 style='text-align:center;'>Bangla Typing Instructions:</h3>"
                + "<ul style='text-align:left;'>"
                + "<li>Ensure your keyboard is set to Bangla layout.</li>"
                + "<li>Use Bangla characters as per your keyboard mapping.</li>"
                + "</ul></html>");
        banglaInstructionLabel.setVerticalAlignment(SwingConstants.TOP);

        // English Instruction Label
        englishInstructionLabel = new JLabel("<html><h3 style='text-align:center;'>English Typing Instructions:</h3>"
                + "<ul style='text-align:left;'>"
                + "<li>Use the standard QWERTY layout for typing.</li>"
                + "<li>Type directly in the text area for English.</li>"
                + "<li>Use punctuation keys for correct writing.</li>"
                + "</ul></html>");
        englishInstructionLabel.setVerticalAlignment(SwingConstants.TOP);

        instructionPanel.add(banglaInstructionLabel);
        instructionPanel.add(englishInstructionLabel);

        add(instructionPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        System.setProperty("file.encoding", "UTF-8");
        banglaButton = new JButton("Bangla Typing");
        englishButton = new JButton("English Typing");
        banglaButton.addActionListener(this);
        englishButton.addActionListener(this);
        buttonPanel.add(banglaButton);
        buttonPanel.add(englishButton);

        add(buttonPanel, BorderLayout.SOUTH);

        fontSelection = new FontSelection();

        setLocationRelativeTo(null);

        setVisible(true);
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (countdownTimer == null) {
            startCountdown(60); // Start countdown for 1 minute (60 seconds)
        }
        if (e.getKeyCode() == KeyEvent.VK_0) {
            System.exit(0); // Exit the application
        }
    }

    public void keyReleased(KeyEvent e) {
        if (typingArea.isEditable()) {
            String typedText = typingArea.getText().trim();
            if (!typedText.isEmpty()) {
                highlightText.highlightText(typedText, referenceText, typingArea);
                speedCounter.updateTypingStatistics(typedText, referenceText);
                accuracyCounter.updateTypingStatistics(typedText, referenceText);
                if (typedText.equals(referenceText)) {
                    endTypingTest(); // End the typing test if the text matches
                }
            }
        }
    }

    private void startCountdown(int seconds) {
        timeLeft = seconds;
        countdownTimer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                    int minutes = timeLeft / 60;
                    int seconds = timeLeft % 60;
                    timerLabel.setText(String.format("Time Left: %d:%02d", minutes, seconds));
                } else {
                    countdownTimer.stop();
                    timerLabel.setText("Time's up!");
                    showTypingStatistics();
                    typingArea.setEditable(false);
                }
            }
        });
        countdownTimer.start();
    }

    private void showTypingStatistics() {
        double wpm = speedCounter.calculateWPM();
        double accuracy = accuracyCounter.calculateAccuracy();
        JOptionPane.showMessageDialog(TypingInterface.this,
                String.format("Your WPM: %.2f\nYour Accuracy: %.2f%%", wpm, accuracy));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == banglaButton) {
            dispose();
            createTypingFrame("Bangla Typing Mode", true);
        } else if (e.getSource() == englishButton) {
            dispose();
            createTypingFrame("English Typing Mode", false);
        }
    }

    private void createTypingFrame(String title, boolean isBangla) {
        JFrame typingFrame = new JFrame(title);
        typingFrame.setSize(600, 400);
        typingFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        typingFrame.setLayout(new BorderLayout());

        JTextPane nonEditableArea = new JTextPane();
        typingArea = new JTextPane();

        JPanel typingPanel = new JPanel(new BorderLayout());
        typingPanel.add(new JScrollPane(nonEditableArea), BorderLayout.CENTER);

        Passages passages = new Passages();
        if (isBangla) {
            referenceText = passages.getRandomPassage(true);
            fontSelection.setBanglaFont(nonEditableArea, typingArea);
        } else {
            referenceText = passages.getRandomPassage(false);
            fontSelection.setEnglishFont(nonEditableArea, typingArea);
        }

        nonEditableArea.setText(referenceText);
        nonEditableArea.setEditable(false);

        typingPanel.add(new JScrollPane(typingArea), BorderLayout.SOUTH);

        typingArea.addKeyListener(this);
        typingFrame.add(typingPanel, BorderLayout.CENTER);

        timerLabel = new JLabel("Time Left: 1:00");
        typingFrame.add(timerLabel, BorderLayout.NORTH);

        endButton = new JButton("End Test");
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endTypingTest();
            }
        });
        typingFrame.add(endButton, BorderLayout.SOUTH);

        speedCounter = new Speed();
        accuracyCounter = new Accuracy();
        highlightText = new HighlightText();

        typingFrame.setLocationRelativeTo(null);
        typingFrame.setVisible(true);
    }

    private void endTypingTest() {
        if (countdownTimer != null) {
            countdownTimer.stop();
        }
        showTypingStatistics();
        typingArea.setEditable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TypingInterface();
            }
        });
    }
}
