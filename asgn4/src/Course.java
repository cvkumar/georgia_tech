import java.util.ArrayList;
import java.util.List;

/**
 * Created by kumarcal on 9/30/17.
 */
public class Course {

    private Integer courseID;
    private String name;
    private Integer cost;
    private List<Integer> prereqIDs;

    public Course(Integer courseID, String name, Integer cost) {
        this.courseID = courseID;
        this.name = name;
        this.cost = cost;
        this.prereqIDs = new ArrayList<Integer>();
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void addPrereq(Course prereq) {
        this.prereqIDs.add(prereq.getCourseID());
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", prereqIDs=" + prereqIDs +
                '}';
    }
}
