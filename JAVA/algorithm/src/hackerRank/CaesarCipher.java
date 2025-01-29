package hackerRank;

public class CaesarCipher {
    public static String caesarCipher(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c>='a' && c<='z') {
                int temp = (c-'a'+k)%26;
                char en = (char)(temp+'a');
                sb.append(en);
            }else if(c>='A' && c<='Z'){
                int temp = (c-'A'+k)%26;
                char en = (char)(temp+'A');
                sb.append(en);
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
