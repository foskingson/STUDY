package order.core.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import order.core.common.MyLogger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        System.out.println("mylogger : "+ myLogger.getClass());
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);
        myLogger.log("컨트롤러 테스트");
        logDemoService.logic("testid");
        return "OK!";
    }
    
    
    
}
