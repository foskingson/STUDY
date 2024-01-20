package Basic;

import javax.swing.JOptionPane;

import org.opentutorials.iot.DimmingLights;
import org.opentutorials.iot.Elevator;
import org.opentutorials.iot.Lighting;
import org.opentutorials.iot.Security;

/**
 * inout
 */
public class inout {

    public static void main(String[] args) {
        String id=JOptionPane.showInputDialog("입력 : ");   // 자바의 입력문
        String strBright=JOptionPane.showInputDialog("밝기: ");
        double bright=Double.parseDouble(strBright);    // 문자열을 float형으로 전환

        // 엘리베이터 부르기
        Elevator myElevator=new Elevator(id+"java apt 507");
        myElevator.callForUp(1);

        // 시큐리티 해제
        Security mySecurity=new Security(id+"1206");
        mySecurity.off();

        // 불키기
        Lighting hallLighting=new Lighting(id+"1212/lamp");
        hallLighting.on();

        Lighting floorLamp= new Lighting(id+"2121/lamp");
        floorLamp.on();

        DimmingLights moodLamp=new DimmingLights(id+" mood");
        moodLamp.setBright(bright);
        moodLamp.on();
    }
}