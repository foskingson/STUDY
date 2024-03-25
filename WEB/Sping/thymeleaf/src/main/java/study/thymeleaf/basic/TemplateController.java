package study.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/template")
public class TemplateController {

    @GetMapping("/fragment")    // 웹에서 공통으로 사용되는 부분 조각 가져와서 여러군데 쓰는 방법(푸터)
    public String template() {
        return "template/fragment/fragmentMain";
    }

    @GetMapping("/layout")  // 템플릿 레이아웃1 헤더 부분만 부분적으로 사용
    public String layout() {
        return "template/layout/layoutMain";
    }

    @GetMapping("/layoutExtend")    // 템플릿 레이아웃2 전체적인 틀안에 부분적으로 넣어서 사용  (내용은 유지한채 틀만 바꿀 때)
    public String layoutExtends() {
        return "template/layoutExtend/layoutExtendMain";
    }
    
}
