package id.co.juaracoding.util;

/**
 * Utility class untuk operasi sederhana terhadap String.
 */
public class TextUtil {

    /**
     * Mengecek apakah sebuah text merupakan palindrome.
     *
     * Aturan:
     * - Tidak membedakan huruf besar dan kecil.
     * - Mengabaikan karakter non-alphanumeric.
     *
     * @param text text yang akan diperiksa
     * @return true apabila text merupakan palindrome
     */
    public boolean isPalindrome(String text) {
        if (text == null) {
            return false;
        }

        String normalizedText = text
                .replaceAll("[^a-zA-Z0-9]", "")
                .toLowerCase();

        String reversedText = new StringBuilder(normalizedText)
                .reverse()
                .toString();

        return normalizedText.equals(reversedText);
    }

    /**
     * Menghitung jumlah huruf vokal pada sebuah text.
     *
     * Huruf vokal yang dihitung:
     * a, i, u, e, o
     *
     * @param text text yang akan dihitung
     * @return jumlah huruf vokal
     */
    public int countVowels(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        int count = 0;

        String normalizedText = text.toLowerCase();

        for (char character : normalizedText.toCharArray()) {
            if (character == 'a'
                    || character == 'i'
                    || character == 'u'
                    || character == 'e'
                    || character == 'o') {

                count++;
            }
        }

        return count;
    }
}