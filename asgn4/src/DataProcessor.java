import java.util.List;
import java.util.Map;

/**
 * Created by kumarcal on 10/4/17.
 */
public class DataProcessor {

    public static String MISSING_PREREQ = "denied: missing prerequisites";
    public static String COURSE_MISSING_INSTRUCTOR = "denied: not being offered";
    public static String REQUEST_GRANTED = "granted";

    private Map<Integer, Instructor> instructorsByCourseID;
    private List<CourseRequest> courseRequests;
    private Map<Integer, List<Record>> studentToRecordsList;
    private Map<Integer, Course> courses;
    private Map<Integer, Student> students;

    public DataProcessor(Map<Integer, Instructor> instructorsByCourseID, List<CourseRequest> courseRequests, Map<Integer, List<Record>> studentToRecordsList, Map<Integer, Course> courses, Map<Integer, Student> students) {
        this.instructorsByCourseID = instructorsByCourseID;
        this.courseRequests = courseRequests;
        this.studentToRecordsList = studentToRecordsList;
        this.courses = courses;
        this.students = students;
    }

    public void processAndDisplayCourseRequests() {

        System.out.println("request processing");

        for (CourseRequest courseRequest : courseRequests) {
            Course course = courses.get(courseRequest.getCourseID());
            List<Record> studentToRecords = studentToRecordsList.get(courseRequest.getStudentID());
            String courseRequestResponse = null;

            if (isCourseMissingPreReq(course, studentToRecords, courseRequestResponse)) {
                courseRequestResponse = MISSING_PREREQ;
            } else if (isCourseMissingInstructor(instructorsByCourseID, courseRequest)) {
                courseRequestResponse = COURSE_MISSING_INSTRUCTOR;
            } else {
                courseRequestResponse = REQUEST_GRANTED;
            }

            System.out.println(String.format("%d, %d: %s", courseRequest.getStudentID(), courseRequest.getCourseID(), courseRequestResponse));
        }
    }

    private boolean isCourseMissingInstructor(Map<Integer, Instructor> instructorsByCourseID, CourseRequest courseRequest) {
        return instructorsByCourseID.get(courseRequest.getCourseID()) == null;
    }

    private boolean isCourseMissingPreReq(Course course, List<Record> studentToRecords, String courseRequestResponse) {
        for (Integer preReqID : course.getPrereqIDs()) {
            boolean hasPreReq = false;
            for (Record record : studentToRecords) {
                if (preReqID == record.getCourseID() && record.getGrade().isPassingGrade()) {
                    hasPreReq = true;
                    break;
                }
            }
            if (!hasPreReq) {
                return true;
            }
        }
        return false;
    }

    public void displayStudentStatusAndCosts() {

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

    public void displayCourses() {

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
