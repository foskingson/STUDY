package InfrenProject.test;

public class Quiz12 {
    public static void main(String[] args) {
        Packaging packaging = new Packaging();

        Runnable productA = new Runnable() {
            @Override
            public void run(){
                for(int i=1;i<=5;i++){
                    packaging.pack("A 상품", i);
                }
                System.out.println("== A 상품 준비 완료 ==");
            }
        };

        Runnable productB = new Runnable() {
            @Override
            public void run(){
                for(int i=1;i<=5;i++){
                    packaging.pack("B 상품", i);
                }
                System.out.println("== B 상품 준비 완료 == ");
            }
        };

        Thread threadA = new Thread(productA);
        Thread threadB = new Thread(productB);
        
        threadA.start();
        threadB.start();

        try {
            threadA.join(); // threadA가 종료될 때까지 기다림
            threadB.join(); // threadB가 종료될 때까지 기다림
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("##세트 상품 포장 시작##");
        for(int i=1;i<=5;i++){
            System.out.println("세트 상품 포장 " + i+"/5");
        }
        System.out.println("%%세트 상품 포장 완료%%");
        
    }
}


class Packaging{
    void pack(String name, int count){
        System.out.println(name + " 준비 " + count+"/5");
    }
    
}