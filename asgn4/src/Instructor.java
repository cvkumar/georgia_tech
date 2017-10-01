/**
 * Created by kumarcal on 9/30/17.
 */
public class Instructor {

    private Integer instructorID;
    private String name;
    private String officeHours;
    private String emailAddress;
    private Integer courseID;

    public Instructor(Integer instructorID, String name, String officeHours, String emailAddress, Integer courseID) {
        this.instructorID = instructorID;
        this.name = name;
        this.officeHours = officeHours;
        this.emailAddress = emailAddress;
        this.courseID = courseID;
    }

    public Integer getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(Integer instructorID) {
        this.instructorID = instructorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorID='" + instructorID + '\'' +
                ", name='" + name + '\'' +
                ", officeHours='" + officeHours + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", courseID='" + courseID + '\'' +
                '}';
    }
}
