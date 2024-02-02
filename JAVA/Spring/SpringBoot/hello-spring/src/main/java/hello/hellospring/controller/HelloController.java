package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;


@Controller
public class HelloController{

    @GetMapping("hello")    //get방식으로 url매칭
    public String hello(Model model) {
        model.addAttribute("data", "hello~111!!");
        return "hello"; //templates의 hello를 찾아서 렌더링 (Thymeleaf템플릿 엔진이 처리)
    }

    @GetMapping("hello-mvc")   
    public String helloMvc(@RequestParam("name") String name, Model model) {
        // http://localhost:8080/hello-mvc?name=1111 이런식으로 뒤에 name값을 줘야 오류가 안난다.
        model.addAttribute("name",name);
        return "hello-template";
        
    }

    @GetMapping("hello-string")   
    @ResponseBody   // return에 직접 데이터를 넣어주겠다는 뜻
    public String helloString(@RequestParam("name") String name) {

        return "hello " + name; //name이 spring이라면 "hello spring" 이라는 뜻
        // 문자열일 경우 return한게 템플릿을 거치지 않고 http응답에 넣어서 바로 웹에 표시됨
        
    }

    @GetMapping("hello-api")
    @ResponseBody   // @ResponseBody를 해놓고 객체를 반환하면 기본적으로 json으로 반환
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // 객체가 들어오면 json형식으로 http응답에 넣어서 보낸다.
        
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
    
}