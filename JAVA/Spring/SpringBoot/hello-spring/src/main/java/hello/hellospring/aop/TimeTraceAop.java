package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
//AOP 메서드 시간 검증 공통로직
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("Start: "+joinPoint.toString());
        try{
            Object result= joinPoint.proceed();
            return result;
        } finally{
            long finish=System.currentTimeMillis();
            long timeMs=finish-start;
            System.out.println("End: "+joinPoint.toString()+" "+timeMs+"ms");
        }
        
    }
}
