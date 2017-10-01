import edu.gatech.Scratchpad;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Map<Integer, Course> courses = new HashMap<Integer, Course>();
        Map<Integer, Instructor> instructors = new HashMap<Integer, Instructor>();
        Map<Integer, Student> students = new HashMap<Integer, Student>();
        Map<Integer, DegreeProgram> degreePrograms = new HashMap<Integer, DegreeProgram>();
        Map<Integer, Record> records = new HashMap<Integer, Record>();

        //file ingestion
        Scratchpad managementConsole = new Scratchpad();
//        System.out.println(System.getProperty("user.dir"));
        String[] managementSystemFiles = {"test/courses.csv", "test/instructors.csv", "test/students.csv", "test/prereqs.csv", "test/programs.csv", "test/listings.csv", "test/records.csv", "test/requests.csv"};
        List<String[]> tokensGrid;
        for (String fileName : managementSystemFiles) {
            tokensGrid = managementConsole.uploadFileContents(fileName);

            switch (fileName) {
                case "test/courses.csv":
                    for (String[] tokens : tokensGrid) {
                        int courseID = Integer.parseInt(tokens[0]);
                        Course course = new Course(courseID, tokens[1], Integer.parseInt(tokens[2]));
//                        System.out.println("Adding Course: " + course);
                        courses.put(courseID, course);
                    }
                    break;
                case "test/instructors.csv":
                    for (String[] tokens : tokensGrid) {
                        int instructorID = Integer.parseInt(tokens[0]);
                        Instructor instructor = new Instructor(instructorID, tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]));
//                        System.out.println("Adding Instructor: " + instructor);
                        instructors.put(instructorID, instructor);
                    }
                    break;
                case "test/students.csv":
                    for (String[] tokens : tokensGrid) {
                        int studentID = Integer.parseInt(tokens[0]);
                        Student student = new Student(studentID, tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]));
//                        System.out.println("adding student: " + student);
                        students.put(studentID, student);
                    }
                    break;
                case "test/prereqs.csv":
                    for (String[] tokens : tokensGrid) {
                        int courseID = Integer.parseInt(tokens[0]);
                        int prereqID = Integer.parseInt(tokens[1]);
                        Course prereq = courses.get(prereqID);
                        Course course = courses.get(courseID);

//                        System.out.println("Adding: " + courses.get(prereqID).getName() + " to " +  course.getName());
                        course.addPrereq(prereq);
                    }
                    break;
                case "test/programs.csv":
                    for (String[] tokens : tokensGrid) {
                        int degreeProgramID = Integer.parseInt(tokens[0]);
                        DegreeProgram degreeProgram = new DegreeProgram(degreeProgramID, tokens[1]);

//                        System.out.println("adding degree program: " + degreeProgram);
                        degreePrograms.put(degreeProgramID, degreeProgram);
                    }

                    break;
                case "test/listings.csv":
                    for (String[] tokens : tokensGrid) {
                        int degreeProgramID = Integer.parseInt(tokens[0]);
                        int courseID = Integer.parseInt(tokens[1]);
                        Course course = courses.get(courseID);
                        DegreeProgram degreeProgram = degreePrograms.get(degreeProgramID);

//                        System.out.println("adding course: " + course.getName() + " to " + degreeProgram.getName());
                        degreeProgram.addCourse(course);
                    }
                    break;
                case "test/records.csv":
//                System.out.println(tokens[0] + ", " + tokens[1] + ", " + tokens[2] + ", " + tokens[3] + ", " + tokens[4]);
                    for (String[] tokens : tokensGrid) {
                        int studentID = Integer.parseInt(tokens[0]);
                        int courseID = Integer.parseInt(tokens[1]);
                        char grade = tokens[2].charAt(0);
                        int term = Integer.parseInt(tokens[4]);
                        Record record = new Record(studentID, courseID, grade, tokens[3], term);

//                        System.out.println(String.format("%s got a '%c' in %s",
//                                students.get(record.getStudentID()).getName(), record.getGrade(), courses.get(record.getCourseID()).getName()));
                        records.put(studentID, record);
                    }

                    break;
                case "test/requests.csv":
//                System.out.println(tokens[0] + ", " + tokens[1]);
                    break;
                default:
                    System.out.println("# error: unknown input file name");
                    break;
            }
        }

        //TEST INGESTION
//        System.out.println(courses);
//        System.out.println(instructors);
//        System.out.println(students);
//        System.out.println(degreePrograms);
//        System.out.println(records);

    }
}
