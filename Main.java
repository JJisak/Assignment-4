import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("./student-master-list.csv"));
        FileWriter writer1 = new FileWriter("course1.csv");
        FileWriter writer2 = new FileWriter("course2.csv");
        FileWriter writer3 = new FileWriter("course3.csv");

        String line = reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            String courseName = parts[2].split(" ")[0];
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
                    throw new IllegalArgumentException("Invalid course: " + parts[2]);
            }

            String[][] students = new String[100][4];
            int studentCount = 0;
            students[studentCount++] = parts;

            while ((line = reader.readLine()) != null && line.split(",")[2].startsWith(courseName)) {
                students[studentCount++] = line.split(",");
            }

            Arrays.sort(students, 0, studentCount, new Comparator<String[]>() {
                public int compare(String[] s1, String[] s2) {
                    return Integer.compare(Integer.parseInt(s2[3]), Integer.parseInt(s1[3]));
                }
            });

            for (int i = 0; i < studentCount; i++) {
                String[] student = students[i];

                switch (course) {
                    case 1:
                        for (int j = 0; j < student.length; j++) {
                            writer1.write(student[j]);
                            if (j < student.length - 1) {
                                writer1.write(",");
                            }
                        }
                        writer1.write("\n");
                        break;
                    case 2:
                        for (int j = 0; j < student.length; j++) {
                            writer2.write(student[j]);
                            if (j < student.length - 1) {
                                writer2.write(",");
                            }
                        }
                        writer2.write("\n");
                        break;
                    case 3:
                        for (int j = 0; j < student.length; j++) {
                            writer3.write(student[j]);
                            if (j < student.length - 1) {
                                writer3.write(",");
                            }
                        }
                        writer3.write("\n");
                        break;
                }
            }
        }

        reader.close();
        writer1.close();
        writer2.close();
        writer3.close();
    }
}
