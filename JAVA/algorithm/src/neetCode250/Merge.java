package neetCode250;

import java.util.ArrayList;
import java.util.List;

public class Merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx1=0, idx2=0;

        List<Integer> temp = new ArrayList<>();
        
        while (idx1<m && idx2<n) {
            if (nums1[idx1]<nums2[idx2]) {
                temp.add(nums1[idx1]);
                idx1++;
            }else{
                temp.add(nums2[idx2]);
                idx2++;
            }
        }

        while (idx1<m) {
            temp.add(nums1[idx1]);
            idx1++;
        }

        while (idx2<n) {
            temp.add(nums2[idx2]);
            idx2++;
        }

        for (int i = 0; i < temp.size(); i++) {
            nums1[i] = temp.get(i);
        }
    }
}
