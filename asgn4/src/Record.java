import java.util.Date;

/**
 * Created by kumarcal on 9/30/17.
 */
public class Record {

    private Integer studentID;
    private Integer courseID;
    private Grade grade;
    private String year;
    private Integer term;

    public Record(Integer studentID, Integer courseID, Grade grade, String year, Integer term) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
        this.year = year;
        this.term = term;
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

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "Record{" +
                "studentID='" + studentID + '\'' +
                ", courseID='" + courseID + '\'' +
                ", grade=" + grade +
                ", year=" + year +
                ", term=" + term +
                '}';
    }
}
