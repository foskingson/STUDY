package neetCode250;

public class ConvertToTitle {
    public static String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();

        while (columnNumber>0) {
            columnNumber--;
            char temp = (char)(columnNumber%26+'A');
            sb.insert(0, temp);
            columnNumber  = columnNumber/26;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(701));
    }   
}
