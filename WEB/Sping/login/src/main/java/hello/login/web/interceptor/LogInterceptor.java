package hello.login.web.interceptor;

import java.util.UUID;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    
    private static final String LOG_ID = "LogId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI=request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        request.setAttribute(LOG_ID, uuid);

        //@RequestMapping: HandlerMethod
        //정적 리소스: ResourceHttpRequestHandler

        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod)handler;  // 호출할 컨트롤러 메서드의 모든 정보가 포함됨
        }

        log.info("REQUEST [{}][{}][{}]",uuid,requestURI,handler);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        log.info("포스트핸들 [{}]",modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        String requestURI = request.getRequestURI();
        Object logId = request.getAttribute(LOG_ID);
        log.info("RESPONSE [{}][{}][{}]",logId,requestURI,handler);

        if(ex!=null){
            log.error("애프터 error!!!!", ex);
        }
    }

}
