import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("./student-master-list.csv"));
        FileWriter writer1 = new FileWriter("course1.csv");
        FileWriter writer2 = new FileWriter("course2.csv");
        FileWriter writer3 = new FileWriter("course3.csv");

        String line = reader.readLine();
        List<String[]> allStudents = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            allStudents.add(parts);
        }

        Collections.sort(allStudents, new Comparator<String[]>() {
            public int compare(String[] s1, String[] s2) {
                int cmp = s1[2].compareTo(s2[2]);
                if (cmp == 0) {
                    return Integer.compare(Integer.parseInt(s2[3]), Integer.parseInt(s1[3]));
                }
                return cmp;
            }
        });

        int course1Count = 0;
        int course2Count = 0;
        int course3Count = 0;

        for (String[] student : allStudents) {
            String courseName = student[2].split(" ")[0];
            int course;

            switch (courseName) {
                case "COMPSCI":
                    course = 1;
                    break;
                case "STAT":
                    course = 2;
                    break;
                case "APMTH":
                    course = 3;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid course: " + student[2]);
            }

            switch (course) {
                case 1:
                    writeStudent(writer1, student);
                    course1Count++;
                    break;
                case 2:
                    writeStudent(writer2, student);
                    course2Count++;
                    break;
                case 3:
                    writeStudent(writer3, student);
                    course3Count++;
                    break;
            }
        }

        if (course1Count + course2Count + course3Count != 100) {
            throw new IllegalStateException("count doesn't match");
        }

        reader.close();
        writer1.close();
        writer2.close();
        writer3.close();
    }

    private static void writeStudent(FileWriter writer, String[] student) throws Exception {
        for (int i = 0; i < student.length; i++) {
            writer.write(student[i]);
            if (i < student.length - 1) {
                writer.write(",");
            }
        }
        writer.write("\n");
    }
}
