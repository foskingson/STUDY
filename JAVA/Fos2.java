import java.io.File;
import java.io.IOException;

public class Fos2 {
    public static void main(String[] args) {
        File folder = new File("A");
        folder.mkdir();      // 폴더 생성
        // "A/B/c"처럼 여러개의 디렉토리를 생성할때는 mkdirs()를 사용한다.
        if(folder.exists()){
            System.out.println("폴더 이름 : "+folder.getName());
            System.out.println("폴더 절대 경로 : "+folder.getAbsolutePath());
        }
    }
}
