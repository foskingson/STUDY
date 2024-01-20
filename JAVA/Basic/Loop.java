public class Loop {

    public static void main(String[] args) {
        int [] arr=new int[3];
        for(int i=0;i<3;i++){
            arr[i]=i;
            System.out.println(arr[i]);
        }

        int [] arr2 = {7,8,9};
        int k=0;
        while(k<3){
            System.out.println(arr2[k]);
            k++;
        }

    }
}
