import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tortoiselala
 */
public class StudentInfoLoader {
    public static List<Student> loader(String filePath) throws IOException, IllegalFileFormatException {
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = null;
        int lineCounter = 0;
        List<Student> resultSet = new LinkedList<>();
        while((line = reader.readLine()) != null){
            lineCounter++;
            resultSet.add(parser(line, lineCounter));
        }
        reader.close();
        fileReader.close();
        return resultSet;

    }

    private static Student parser(String line, int lineNumber) throws IllegalFileFormatException {
        String[] elem = line.split(" ");
        if(elem.length != 5){
            throw new IllegalFileFormatException("无法解析文件内容，第" + lineNumber + "行，内容为" + line);
        }

        Student student = new Student();
        try {
            student.setUid(elem[0]);
            student.setName(elem[1]);
            student.setMath(Double.valueOf(elem[2]));
            student.setEnglish(Double.valueOf(elem[3]));
            student.setComputer(Double.valueOf(elem[4]));

            student.setTotal(student.getMath() + student.getEnglish() + student.getComputer());
        }catch(Throwable e){
            throw new IllegalFileFormatException("数据无法解析， 无法解析的内容为，第" + lineNumber + "行，内容为" + line);
        }
        return student;
    }
}
