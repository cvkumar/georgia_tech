import edu.gatech.Scratchpad;


import java.util.*;

public class Main {

    public static String MISSING_PREREQ = "denied: missing prerequisites";
    public static String COURSE_NOT_OFFERED = "denied: not being offered";
    public static String REQUEST_GRANTED = "granted";

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
//        System.out.println(System.getProperty("user.dir"));
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
//                System.out.println(tokens[0] + ", " + tokens[1] + ", " + tokens[2] + ", " + tokens[3] + ", " + tokens[4]);
                    for (String[] tokens : tokensGrid) {
                        int studentID = Integer.parseInt(tokens[0]);
                        int courseID = Integer.parseInt(tokens[1]);
                        Grade grade = Grade.valueOf(tokens[2]);
                        int term = Integer.parseInt(tokens[4]);
                        Record record = new Record(studentID, courseID, grade, tokens[3], term);

                        System.out.println(String.format("%s got a '%s' in %s",
                                students.get(record.getStudentID()).getName(), record.getGrade(), courses.get(record.getCourseID()).getName()));

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
//                System.out.println(tokens[0] + ", " + tokens[1]);
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

        displayCourses(instructorsByCourseID, courses);
        displayStudentStatusAndCosts(studentToRecordsList, courses, students);


        /*
        request processing
1024, 2: granted
1012, 3: granted
1015, 2: granted
1009, 10: denied: missing prerequisites
1009, 7: denied: not being offered
1004, 10: granted
1021, 4: granted
         */

        /*
        The request shall be accompanied by the message “denied: missing
prerequisites” if the student is missing one or more prerequisites; or, by the message “denied:
not being offered” if there are no instructors teaching the course.

prereqs:
-for each preReq in list of preReqs (course.getPreReqs)
see if its in student
         */
        System.out.println("request processing");
        for (CourseRequest courseRequest : courseRequests) {
            Course course = courses.get(courseRequest.getCourseID());
            List<Record> studentToRecords = studentToRecordsList.get(courseRequest.getStudentID());
            String courseRequestResponse = null;

            for (Integer preReqID : course.getPrereqIDs()) {
                boolean hasPreReq = false;
                for (Record record : studentToRecords) {
                    if (preReqID == record.getCourseID() && record.getGrade().isPassingGrade()) {
                        hasPreReq = true;
                    }
                }
                if (!hasPreReq) {
                    courseRequestResponse = MISSING_PREREQ;
                }
            }

            //check if course is offered

            if (courseRequestResponse == null) {
                courseRequestResponse = REQUEST_GRANTED;
            }
//            System.out.println(String.format("%d, %d: %s", courseRequest.getStudentID(), courseRequest.getCourseID(), ));
        }

    }

    public static void displayStudentStatusAndCosts(Map<Integer, List<Record>> studentToRecordsList, Map<Integer, Course> courses, Map<Integer, Student> students) {

        System.out.println("student status and costs");
        for (Map.Entry<Integer, List<Record>> studentToRecords : studentToRecordsList.entrySet()) {
            Integer totalCost = 0;
            int numberOfCoursesPassed = 0;
            for (Record record : studentToRecords.getValue()) {
                totalCost += courses.get(record.getCourseID()).getCost();
                if (record.getGrade().isPassingGrade()) {
                    numberOfCoursesPassed++;
                }
            }
            System.out.println(String.format("%d: %s passed %d course(s) for $%d",
                    studentToRecords.getKey(), students.get(studentToRecords.getKey()).getName(), numberOfCoursesPassed, totalCost));
        }
    }

    public static void displayCourses(Map<Integer, Instructor> instructorsByCourseID, Map<Integer, Course> courses) {

        System.out.println("courses being taught");
        for (Map.Entry<Integer, Course> courseEntry : courses.entrySet()) {
            Instructor instructor = instructorsByCourseID.get(courseEntry.getKey());
            if (instructor != null) {
                System.out.println(String.format("%d: %s by %d: %s", courseEntry.getKey(), courseEntry.getValue().getName(),
                        instructor.getInstructorID(), instructor.getName()));
            }
        }
    }
}
