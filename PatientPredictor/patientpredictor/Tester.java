//**************************************************************************************
//Name:        Longtin Hang
//Course:	   Java Development CSCI 3381 9:00am
//Description: This program is read patient files and predict the patient progression
// 			   and update the file with newly added patients.
//**************************************************************************************



package patientpredictor;

public class Tester {

	public static void main(String[] args) {
		//create an instance of PatientCollection class
		PatientCollection patientCollection = new PatientCollection();
		
		//upload both the old and new data
		patientCollection.uploadFile("./patientpredictor/data.csv");
		patientCollection.addPatientsFromFile("./patientpredictor/newdata.csv");
		
		//test out getPatient method
		System.out.println("Outputs for get Patient");
		System.out.println(patientCollection.getPatient("15"));
		System.out.println(patientCollection.getPatient("100"));
		
		//test out removePatient method
		System.out.println("\nOutputs for remove Patient");
		System.out.println(patientCollection.removePatient("15"));
		System.out.println(patientCollection.removePatient("15"));
		
		//test out setResultForPatient
		patientCollection.setResultForPatient("30", "CR");
		
		//test out getIds
		System.out.println("\nOutputs for get IDs");
		System.out.println(patientCollection.getIds());
		
		//test out toString
		System.out.println("\nOutputs of Patients");
		System.out.println(patientCollection);
		
		//write to file
		patientCollection.writingToFile("./patientpredictor/test.csv");
	}

}
