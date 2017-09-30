import java.util.List;

/**
 * Created by kumarcal on 9/30/17.
 */
public class DegreeProgram {

    private Integer degreeID;
    private String Name;
    private List<Integer> courseIDs;

    public DegreeProgram(Integer degreeID, String name) {
        this.degreeID = degreeID;
        Name = name;
    }

    public Integer getDegreeID() {
        return degreeID;
    }

    public void setDegreeID(Integer degreeID) {
        this.degreeID = degreeID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void addCourse(Course course) {
        courseIDs.add(course.getCourseID());
    }
}
