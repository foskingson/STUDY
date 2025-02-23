package neetCode250;

public class MergeAlternately {
    public String mergeAlternately(String word1, String word2) {
        int idx1=0, idx2=0;
        StringBuilder sb = new StringBuilder();
        while (idx1<word1.length() && idx2 < word2.length()) {
            sb.append(word1.charAt(idx1));
            sb.append(word2.charAt(idx2));
            idx1++;
            idx2++;
        }

        while (idx1<word1.length()) {
            sb.append(word1.charAt(idx1));
            idx1++;
        }

        while (idx2<word2.length()) {
            sb.append(word2.charAt(idx2));
            idx2++;
        }

        return sb.toString();
    }
}
