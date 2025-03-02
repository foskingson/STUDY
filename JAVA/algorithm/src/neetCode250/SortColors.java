package neetCode250;

public class SortColors {
    public void sortColors(int[] nums) {
        int[] count = new int[3];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==0) {
                count[0]++;
            }else if(nums[i]==1){
                count[1]++;
            }else{
                count[2]++;
            }
        }

        int idx=0;
        for (int i = 0; i < count[0]; i++) {
            nums[idx]=0;
            idx++;
        }

        for (int i = 0; i < count[1]; i++) {
            nums[idx]=1;
            idx++;
        }

        for (int i = 0; i < count[2]; i++) {
            nums[idx]=2;
            idx++;
        }
    }
}
