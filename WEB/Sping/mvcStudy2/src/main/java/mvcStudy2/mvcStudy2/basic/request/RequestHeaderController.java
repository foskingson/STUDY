package mvcStudy2.mvcStudy2.basic.request;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@Slf4j
public class RequestHeaderController {
    
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                        HttpServletResponse response,
                        HttpMethod httpMethod,
                        Locale locale,
                        @RequestHeader MultiValueMap<String,String> headerMap,
                        @RequestHeader("host") String host, // 스트링 헤더는 필수 헤더
                        @CookieValue(value = "myCookie",required = false) String cookie
        ) {
            log.info("request = {}", request);
            log.info("response = {}", response);
            log.info("httpMethod = {}", httpMethod);
            log.info("locale = {}", locale);
            log.info("headerMap = {}", headerMap);
            log.info("header host = {}", host);
            log.info("myCookie = {}", cookie);

            return "ok";
        }


    
}
