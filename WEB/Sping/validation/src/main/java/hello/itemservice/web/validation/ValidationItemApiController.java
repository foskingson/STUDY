package hello.itemservice.web.validation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {
    
    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form,BindingResult bindingResult ) {

        log.info("API 컨트롤러 호출");

        if(bindingResult.hasErrors()){
            log.info("오류 : {}",bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("성공");
        return form;
    }
    
}
