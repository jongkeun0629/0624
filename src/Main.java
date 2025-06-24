import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class Main {
    public static void writeFile(String filename, String content){
        Path filePath = Paths.get(filename);

        try (FileChannel writeChannel = FileChannel.open(
                filePath,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE)){

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(content.getBytes());
            buffer.flip();
            writeChannel.write(buffer);
            System.out.println("파일 작성 완료");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readFile(String filename){
        Path filePath = Paths.get(filename);

        try (FileChannel readChannel = FileChannel.open(filePath, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = readChannel.read(buffer);

            while (bytesRead != -1){
                buffer.flip();

                String chunk = StandardCharsets.UTF_8.decode(buffer).toString();
                System.out.print(chunk);

                buffer.clear();
                bytesRead = readChannel.read(buffer);
            }
            System.out.println("\n읽기 완료");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        writeFile("writeTest.txt", "뭐 먹지?");
        readFile("writeTest.txt");
    }
}