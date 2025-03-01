package neetCode250;

public class TwoSum2 {
    public int[] twoSum(int[] numbers, int target) {
        int st = 0, end =numbers.length-1;
        while (st<end) {
            if (numbers[st]+numbers[end]==target) {
                return new int[]{st+1,end+1};
            }else if(numbers[st]+numbers[end]<target){
                st++;
            }else{
                end--;
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        TwoSum2 t = new TwoSum2();
        t.twoSum(new int[]{100,200,300,500}, 500);
    }
}
