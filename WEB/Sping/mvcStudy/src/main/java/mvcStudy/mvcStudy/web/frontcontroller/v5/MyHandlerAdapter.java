package mvcStudy.mvcStudy.web.frontcontroller.v5;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvcStudy.mvcStudy.web.frontcontroller.ModelView;

public interface MyHandlerAdapter {
    
    boolean supports(Object handler);

    ModelView handle(HttpServletRequest request, HttpServletResponse response,Object handler) throws ServletException,IOException; 
}
