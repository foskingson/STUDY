package mvcStudy.mvcStudy.web.frontcontroller.v3;

import java.util.Map;

import mvcStudy.mvcStudy.web.frontcontroller.ModelView;

public interface ControllerV3 {
    ModelView process(Map<String,String> paramMap);
} 
