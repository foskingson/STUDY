package hackerRank;

public class PageCount {
    public static int pageCount(int n, int p) {
        int totalPages = (n % 2 == 0) ? n + 1 : n;

        return Math.min(p / 2, (totalPages - p) / 2);
    }
    public static void main(String[] args) {
        System.out.println(pageCount(6, 5));
    }
}
