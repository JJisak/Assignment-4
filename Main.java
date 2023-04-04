import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("./student-master-list.csv"));

        FileWriter writer1 = new FileWriter("course1.csv");
        FileWriter writer2 = new FileWriter("course2.csv");
        FileWriter writer3 = new FileWriter("course3.csv");

        String line = reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            int course = Integer.parseInt(parts[2].substring(parts[2].length() - 1));

            List<String[]> students = new ArrayList<>();
            students.add(parts);
            while ((line = reader.readLine()) != null && Integer.parseInt(line.split(",")[2].substring(line.split(",")[2].length() - 1)) == course) {
                students.add(line.split(","));
            }
            Collections.sort(students, new Comparator<String[]>() {
                public int compare(String[] s1, String[] s2) {
                    return Integer.compare(Integer.parseInt(s2[3]), Integer.parseInt(s1[3]));
                }
            });

            for (String[] student : students) {
                if (course == 1) {
                    for (int i = 0; i < student.length; i++) {
                        writer1.write(student[i]);
                        if (i < student.length - 1) {
                            writer1.write(",");
                        }
                    }
                    writer1.write("\n");
                } else if (course == 2) {
                    for (int i = 0; i < student.length; i++) {
                        writer2.write(student[i]);
                        if (i < student.length - 1) {
                            writer2.write(",");
                        }
                    }
                    writer2.write("\n");
                } else if (course == 3) {
                    for (int i = 0; i < student.length; i++) {
                        writer3.write(student[i]);
                        if (i < student.length - 1) {
                            writer3.write(",");
                        }
                    }
                    writer3.write("\n");
                }
            }
        }

        reader.close();
        writer1.close();
        writer2.close();
        writer3.close();
    }
}
