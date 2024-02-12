package order.core.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import order.core.AppConfig;

public class BeanDefinitionTest {
    
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void 빈메타정보확인(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){     // 우리가 만든 빈 객체만 출력하기 (스프링 내부에서 사용하는 Bean제외)
                System.out.println("name = "+ beanDefinitionName + " definition = " + beanDefinition);
            }
        }
    }
    
}
