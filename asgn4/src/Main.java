import edu.gatech.Scratchpad;

public class Main {

	public static void main(String[] args) {
        Scratchpad managementConsole = new Scratchpad();

        String[] managementSystemFiles = {"courses.csv", "instructors.csv", "listings.csv", "prereqs.csv", "programs.csv", "records.csv", "requests.csv", "students.csv"};
        for (String nextFileName : managementSystemFiles) {
            managementConsole.uploadFileContents(nextFileName);
        }
	}
		
}
