import edu.gatech.Scratchpad;


import java.util.*;

public class SchoolSystemLauncher {

    public static void main(String[] args) {

        Map<Integer, Instructor> instructorsByInstructorID = new HashMap<Integer, Instructor>();
        Map<Integer, Instructor> instructorsByCourseID = new HashMap<Integer, Instructor>();
        Map<Integer, DegreeProgram> degreePrograms = new HashMap<Integer, DegreeProgram>();
        List<CourseRequest> courseRequests = new ArrayList<CourseRequest>();

        //sorted in ascending order
        Map<Integer, List<Record>> studentToRecordsList = new TreeMap<Integer, List<Record>>();
        Map<Integer, Course> courses = new TreeMap<Integer, Course>();
        Map<Integer, Student> students = new TreeMap<Integer, Student>();

        //file ingestion
        Scratchpad managementConsole = new Scratchpad();
        String[] managementSystemFiles = {"test/courses.csv", "test/instructors.csv", "test/students.csv", "test/prereqs.csv", "test/programs.csv", "test/listings.csv", "test/records.csv", "test/requests.csv"};

        for (String fileName : managementSystemFiles) {
            List<String[]> tokensGrid = managementConsole.uploadFileContents(fileName);

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
                        instructorsByInstructorID.put(instructorID, instructor);
                        instructorsByCourseID.put(instructor.getCourseID(), instructor);
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
                    for (String[] tokens : tokensGrid) {
                        int studentID = Integer.parseInt(tokens[0]);
                        int courseID = Integer.parseInt(tokens[1]);
                        Grade grade = Grade.valueOf(tokens[2]);
                        int term = Integer.parseInt(tokens[4]);
                        Record record = new Record(studentID, courseID, grade, tokens[3], term);

//                        System.out.println(String.format("%s got a '%s' in %s",
//                                students.get(record.getStudentID()).getName(), record.getGrade(), courses.get(record.getCourseID()).getName()));

                        List<Record> studentRecords = studentToRecordsList.get(studentID);
                        if (studentRecords == null) {
                            studentRecords = new ArrayList<Record>();
                            studentRecords.add(record);
                            studentToRecordsList.put(studentID, studentRecords);
                        } else {
                            studentRecords.add(record);
                        }
                    }

                    break;
                case "test/requests.csv":
                    for (String[] tokens : tokensGrid) {
                        Integer studentID = Integer.parseInt(tokens[0]);
                        Integer courseID = Integer.parseInt(tokens[1]);
                        courseRequests.add(new CourseRequest(studentID, courseID));
                    }
                    break;
                default:
                    System.out.println("# error: unknown input file name");
                    break;
            }
        }

        //TEST INGESTION
//        System.out.println(courses);
//        System.out.println(instructorsByCourseID);
//        System.out.println(instructorsByInstructorID);
//        System.out.println(students);
//        System.out.println(degreePrograms);
//        System.out.println(studentToRecordsList);


        //Analyze Data
        DataProcessor dataProcessor = new DataProcessor(instructorsByCourseID, courseRequests, studentToRecordsList, courses, students);
        dataProcessor.displayCourses();
        dataProcessor.displayStudentStatusAndCosts();
        dataProcessor.processAndDisplayCourseRequests();

    }


}
