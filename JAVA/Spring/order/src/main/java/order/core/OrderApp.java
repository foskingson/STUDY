package order.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import order.core.member.Grade;
import order.core.member.Member;
import order.core.member.MemberService;
import order.core.member.memberServiceImpl;
import order.core.order.Order;
import order.core.order.OrderService;
import order.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = applicationContext.getBean("orderService",OrderService.class);
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);
        
       
        Member member = new Member(1L, "김김김", Grade.VIP);
        memberService.join(member);

        Order order = orderService.creatOrder(1L, "사과", 20000);
        System.out.println(order);
        System.out.println(order.calculatePrice());
    }
    
}
