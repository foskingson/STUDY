package Basic;

import org.opentutorials.iot.Elevator;
import org.opentutorials.iot.Lighting;
import org.opentutorials.iot.Security;


public class iot {
    public static void main(String[] args) {
        // 엘리베이터 부르기
        Elevator myElevator=new Elevator("java apt 507");
        myElevator.callForUp(1);

        // 시큐리티 해제
        Security mySecurity=new Security("1206");
        mySecurity.off();

        // 불키기
        Lighting hallLighting=new Lighting("1212/lamp");
        hallLighting.on();

        Lighting floorLamp= new Lighting("2121/lamp");
        floorLamp.on();

    }
}
