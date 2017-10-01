import java.util.ArrayList;
import java.util.List;

/**
 * Created by kumarcal on 9/30/17.
 */
public class Student {

    private Integer studentID;
    private String name;
    private String address;
    private String phoneNumber;
    private Integer degreeID;
    private List<Record> records;

    public Student(Integer studentID, String name, String address, String phoneNumber, Integer degreeID) {
        this.studentID = studentID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.degreeID = degreeID;
        this.records = new ArrayList<Record>();
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getDegreeID() {
        return degreeID;
    }

    public void setDegreeID(Integer degreeID) {
        this.degreeID = degreeID;
    }

    public void requestCourse(Integer courseID) {
        //TODO: Implement
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", degreeID=" + degreeID +
                '}';
    }
}
