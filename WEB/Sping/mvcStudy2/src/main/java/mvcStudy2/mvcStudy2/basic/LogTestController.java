package mvcStudy2.mvcStudy2.basic;

import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/log-test",method = RequestMethod.GET)
    public String logTest() {
        String name="spring";
        System.out.println("info log = "+name);

        log.trace("trace log = {}",name);
        log.debug("debug log = {}",name);
        log.info("info log = "+name);
        log.warn("warn log = {}",name);
        log.error("error log = {}",name);
        return "ok";
    }
    
}
