import java.io.IOException;

/**
 * @author tortoiselala
 */
public class StudentInfoLoaderTest {
    public static void main(String[] args) throws IOException, IllegalFileFormatException {
        for(Student e: StudentInfoLoader.loader("./src/stu.dat")){
            System.out.println(e);
        }
    }
}
