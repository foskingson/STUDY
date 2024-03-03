package mvcStudy.mvcStudy.web.frontcontroller.v3.controller;

import java.util.Map;

import mvcStudy.mvcStudy.web.frontcontroller.ModelView;
import mvcStudy.mvcStudy.web.frontcontroller.v3.ControllerV3;

public class MemberFormControllerV3 implements ControllerV3{

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
    
}
