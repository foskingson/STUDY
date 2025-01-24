package neetCode250;

class Solution {
    public boolean isPalindrome(String s) {
        s=s.replaceAll("[^a-zA-Z0-9]", "");
        int left = 0, right = s.length()-1;
        char[] c = s.toCharArray();

        while (left<right) {
            if (Character.toUpperCase(c[left])!=Character.toUpperCase(c[right])) {
                return false;
            }    
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome("OP"));
    }
}
