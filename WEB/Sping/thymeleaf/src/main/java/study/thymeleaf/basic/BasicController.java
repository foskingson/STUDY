package study.thymeleaf.basic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;


@Controller
@RequestMapping("/basic")
public class BasicController {
    
    private ArrayList<Object> arrayList;

    @GetMapping("/text-basic")  // 기본 텍스트
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring!");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped") // 이스케이프 기능을 안쓰는 텍스트
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")    // 각종 변수 사용
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);
        
        ArrayList<User> list = new ArrayList<User>();
        list.add(userA);
        list.add(userB);

        Map<String,User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @GetMapping("/basic-objects")   // 기본 객체들 스프링부트3.0부터는 기본 객체 사용이 막혀서 model로 만들어서 사용해야한다.
    public String basicObjects(Model model, HttpServletRequest request, 
    HttpServletResponse response, HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());
        return "basic/basic-objects";
    }

    @GetMapping("/date") // 유틸리티 객체 날짜 표현
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("/link")    // URL 링크 사용
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    @GetMapping("/literal") // 리터럴 
    public String literal(Model model){
        model.addAttribute("data", "Spring~~~");
        return "basic/literal";
    }

    @GetMapping("/operation")   // 연산
    public String operation(Model model){
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    @GetMapping("/attribute")    // 속성 설정
    public String attribute() {
        return "basic/attribute";
    }

    @GetMapping("/each")       // 반복
    public String each(Model model) {
        addUsers(model);
        return "basic/each";
    }
    

    @GetMapping("/condition")   // 조건부 평가
    public String condition(Model model) {
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping("/comments")    // 주석처리
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }

    @GetMapping("/block")   // 블록 (반복 관련)
    public String block(Model model) {
        addUsers(model);
        return "basic/block";
    }

    @GetMapping("/javascript")  // 자바스크립트 인라인 기능
    public String javascript(Model model) {
        model.addAttribute("user", new User("userA", 10));
        addUsers(model);
        return "basic/javascript";
    }
    
    
    private void addUsers(Model model){
        ArrayList<User> list = new ArrayList<User>();
        list.add(new User("UserA", 10));
        list.add(new User("UserB", 20));
        list.add(new User("UserC", 30));

        model.addAttribute("users", list);
    }

    @Component("helloBean")
    static class HelloBean{
        public String hello(String data){
            return "Hello " + data;
        }
    }
    
    @Data
    static class User{
        private String username;
        private int age;
        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
    
}
