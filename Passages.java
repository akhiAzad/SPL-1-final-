import java.util.Random;

public class Passages {
    private final String[] banglaPassages = {
        "অয়ন ও তপন ২ জমজ ভাই কনকনে শীতে টগরকে নিয়ে বেড়াতে যাচ্ছিল।তখন গাছে  থাকা পাকা আঙুরের থোকা দেখে তারা কীভাবে নাগাল পাবে সেই নিয়ে ভাবতে শুরু করল ।",
        "কখনও কখনও সজল সময়-অসময় নিজ মনে বকবক করে।এইজন্য সবাই তাকে পাগল মনে করত।",
        "তখন একজন বলল, “আমি একটা খুব ভালো উপায়ের কথা চিন্তা করেছি ।আমরা যদি বিড়ালটির গলায় ঘণ্টা বেঁধে দি তাহলে বিড়ালটা যে আসছে সেই ঘণ্টার শব্দ শুনেই পরিষ্কারভাবে বোঝা যাবে।”"
    };

    private final String[] englishPassages = {
        "You're a little scary sometimes, you know that? Brilliant... but scary. Do not pity the dead, Harry. Pity the living, and above all, those who live without love.",
        "Driven by hunger, a fox tried to reach some grapes hanging high on the vine but was unable to, although he leaped with all his strength. As he went away, the fox remarked 'Oh, you aren't even ripe yet! I don't need any sour grapes.",
        "A group of mice agree to attach a bell to a cat's neck to warn of its approach in the future, but they fail to find a volunteer to perform the job."
    };

    public String getRandomPassage(boolean isBangla) {
        Random random = new Random();
        if (isBangla) {
            return banglaPassages[random.nextInt(banglaPassages.length)];
        } else {
            return englishPassages[random.nextInt(englishPassages.length)];
        }
    }
}
