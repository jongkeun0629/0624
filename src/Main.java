import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void writeFile(String filename, String content){

        // 기존 방식. finally로 파일 닫아주어야 함
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(filename);
//            writer.write(content);
//        } catch (IOException e){
//            e.printStackTrace();
//        } finally {
//            try {
//                if (writer != null) writer.close();
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//        }

        // try-with-resources. 파일 자동으로 닫는다
        try (FileWriter writer = new FileWriter(filename, true)) {      // 두 번째 인자 true. 이어쓰기
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readFile(String filename){
        try (BufferedReader reader= new BufferedReader(new FileReader(filename))){
            String line;

            // 마지막줄까지
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
//        writeFile("example.txt", "\n이어쓰기");
//        System.out.println("파일 생성 완료");

        readFile("example.txt");
    }
}