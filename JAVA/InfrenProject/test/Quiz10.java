package InfrenProject.test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

class Customer{
    public String name;
    public int age;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    
}

public class Quiz10 {
    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();

        customers.add(new Customer("챈들러", 50));
        customers.add(new Customer("레이첼", 42));
        customers.add(new Customer("모니카", 21));
        customers.add(new Customer("벤자민", 18));
        customers.add(new Customer("제임스", 5));

        customers.stream()
                .map(x -> x.age>=20 ? x.name + " 5000원" : x.name + " 무료")
                .forEach(x-> System.out.println(x));

    }
}
