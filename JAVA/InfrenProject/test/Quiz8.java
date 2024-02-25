package InfrenProject.test;

import chap_08.camera.SpeedCam;
import chap_08.detector.AccidentDetector;
import chap_08.reporter.VideoReporter;

public class Quiz8 {
    public static void main(String[] args) {
        SpeedCam speedCam = new SpeedCam(new AccidentDetector(), new VideoReporter());

        speedCam.detect();
        speedCam.report();
    }
}
