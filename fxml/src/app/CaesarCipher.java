package app;

public class CaesarCipher {
    public static String encrypt(String text, int shift) {
        return transform(text, shift);
    }

    public static String decrypt(String text, int shift) {
        return transform(text, -shift);
    }

    private static String transform(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                int offset = ((c - 'A') + shift + 26) % 26;
                result.append((char) ('A' + offset));
            } else if (c == ' ' || c == '.') {
                result.append(c);
            } else {
                throw new IllegalArgumentException("Neplatný znak: " + c);
            }
        }
        return result.toString();
    }
}
