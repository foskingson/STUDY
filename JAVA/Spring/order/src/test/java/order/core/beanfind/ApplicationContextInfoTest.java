package order.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import order.core.AppConfig;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void 모든빈찾기(){
        String[] beanDefinationNames= ac.getBeanDefinitionNames();
        for (String beanDefinationName : beanDefinationNames) {
            Object bean = ac.getBean(beanDefinationName);
            System.out.println("name = "+ beanDefinationName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void 애플리케이션빈찾기(){
        String[] beanDefinationNames= ac.getBeanDefinitionNames();
        for (String beanDefinationName : beanDefinationNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinationName);
            
            // ROLE_APPLICATION : 일반적으로 사용자가 정의한 빈
            // ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){     // 우리가 만든 빈 객체만 출력하기 (스프링 내부에서 사용하는 Bean제외)
                Object bean = ac.getBean(beanDefinationName);
                System.out.println("name = "+ beanDefinationName + " object = " + bean);
            }
        }
    }
}
