import edu.gatech.Scratchpad;

public class Main {

	public static void main(String[] args) {
        Scratchpad managementConsole = new Scratchpad();
//        System.out.println(System.getProperty("user.dir"));
        String[] managementSystemFiles = {"test/courses.csv", "test/instructors.csv", "test/listings.csv", "test/prereqs.csv", "test/programs.csv", "test/records.csv", "test/requests.csv", "test/students.csv"};
        for (String nextFileName : managementSystemFiles) {
            managementConsole.uploadFileContents(nextFileName);
        }
	}
		
}
