package mvcStudy2.mvcStudy2.basic.requestmapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/mapping/users")
@Setter @Getter
public class MappingClassController {
    private String user;

    @GetMapping
    public String user(){
        return "get users";
    }

    @PostMapping
    public String addUser(){
        return "post users";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){
        return "userId : "+userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId){
        return "update userId : "+userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete userId : "+userId;
    }

}
