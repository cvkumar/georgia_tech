import java.util.ArrayList;
import java.util.List;

/**
 * Created by kumarcal on 9/30/17.
 */
public class DegreeProgram {

    private Integer degreeProgramID;
    private String Name;
    private List<Integer> courseIDs;

    public DegreeProgram(Integer degreeProgramID, String name) {
        this.degreeProgramID = degreeProgramID;
        Name = name;
        courseIDs = new ArrayList<Integer>();
    }

    public Integer getDegreeProgramID() {
        return degreeProgramID;
    }

    public void setDegreeProgramID(Integer degreeProgramID) {
        this.degreeProgramID = degreeProgramID;
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

    @Override
    public String toString() {
        return "DegreeProgram{" +
                "degreeProgramID=" + degreeProgramID +
                ", Name='" + Name + '\'' +
                ", courseIDs=" + courseIDs +
                '}';
    }
}
