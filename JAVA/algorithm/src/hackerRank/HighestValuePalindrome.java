package hackerRank;

import java.util.HashSet;
import java.util.Set;

public class HighestValuePalindrome {
    public static String highestValuePalindrome(String s, int n, int k) {
        int right = n/2 ; 
        int left = (n%2 == 0 ) ? (n-1)/2 : n/2;
        char[] c = s.toCharArray() ;

        Set<Integer> changes = new HashSet<>();
        
        while( left>=0 && right<c.length && k>=0 ) {
            if (c[left] != c[right] ) {
                c[left] = (char)Math.max(c[left], c[right]);
                c[right] = c[left];
                changes.add(left);
                --k;
            }
            --left;
            ++right;
        }
        
        while(k>0 && left < right) {
            ++left;
            --right;
            if (c[left] != '9') {
                if (changes.contains(left))
                    ++k;
                if (left == right) {
                    c[left] = '9';
                    --k;
                } else if ( k>=2 ) {
                    c[left] = '9';
                    c[right] ='9';
                    k -= 2;                
                }
            }
        }
        
        return (k >= 0) ? new String(c) : "-1";
    }

    public static void main(String[] args) {
        highestValuePalindrome("1231", 4, 3);
    }
}
