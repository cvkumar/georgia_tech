/**
 * Created by kumarcal on 10/2/17.
 */
public class CourseRequest {
    private Integer studentID;
    private Integer courseID;

    public CourseRequest(Integer studentID, Integer courseID) {
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "CourseRequest{" +
                "studentID=" + studentID +
                ", courseID=" + courseID +
                '}';
    }
}
