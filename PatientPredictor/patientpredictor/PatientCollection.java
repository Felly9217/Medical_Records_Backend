package patientpredictor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PatientCollection implements PatientCollectionADT {
	private Map<String, Patient> PatientMap;

	public PatientCollection() {
		PatientMap = new HashMap<String, Patient>();
		uploadFile("./patientpredictor/data.csv");
		System.out.println(addPatientsFromFile("./patientpredictor/newdata.csv"));
	}

	public Patient getPatient(String id) {
		return PatientMap.containsKey(id) ? PatientMap.get(id) : null;
	}
	// Return the patient with the given id. Return void if the id does
	// not exist in the collection

	public Patient removePatient(String id) {
		return PatientMap.containsKey(id) ? PatientMap.remove(id) : null;
	}
	// Remove and return the Patient with the given id. Return void if the id does
	// not exist.

	public void setResultForPatient(String id, String result) {
		Patient p = PatientMap.get(id);
		p.setResult(result);
	}
	// Set the result field for the patient with given id.

	public ArrayList<String> getIds() {
		ArrayList<String> toReturn = new ArrayList<String>();
		for (String key : PatientMap.keySet()) {
			Patient p = PatientMap.get(key);
			toReturn.add(p.getId());
		}

		return toReturn;
	}
	// Return an ArrayList with all of the collection's patient ids

	public String addPatientsFromFile(String fileName) {
		BufferedReader lineReader = null;
		String toReturn = "Success!";
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String readLine[];
			String line;
			while ((line = lineReader.readLine()) != null) {
				readLine = line.split(",");
				ArrayList<Double> genome = new ArrayList<Double>();
				String g;
				String id = readLine[0];
				for (int i = 1; i < 4777; i++) {
					g = readLine[i];
					genome.add(Double.parseDouble(g));
				}
				if (!PatientMap.containsKey(id)) {
					Patient p = new Patient("unknown", "unknown", id, genome);
					PatientMap.put(id, p);
				} else {
					toReturn = "This " + id + " is already in the system";
				}
			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");
			try {
				lineReader = new BufferedReader(
						new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				String readLine[];
				while ((line = lineReader.readLine()) != null) {
					readLine = line.split(",");
					ArrayList<Double> genome = new ArrayList<Double>();
					String g;
					if (readLine.length == 4779) {
						String result = readLine[0];
						String predict = readLine[1];
						String id = readLine[2];
						for (int i = 3; i < 4779; i++) {
							g = readLine[i];
							genome.add(Double.parseDouble(g));
						}
						Patient p = new Patient(result, predict, id, genome);
						PatientMap.put(id, p);
					} else if (readLine.length == 4777) {
						String id = readLine[0];
						for (int i = 1; i < 4777; i++) {
							g = readLine[i];
							genome.add(Double.parseDouble(g));
						}
						if (!PatientMap.containsKey(id)) {
							Patient p = new Patient("unknown", "unknown", id, genome);
							PatientMap.put(id, p);
						} else {
							toReturn = "The number ID " + id + " is already in the system";
						}
					}
				}
			} catch (Exception e2) {
				System.err.println(
						"there was a problem with the file reader, try again.  either no such file or format error");
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
		return toReturn;
	}
	// Method reads all lines from comma separated file named fileName.
	// Each line must contain a unique patient identifier followed by exactly 4776
	// doubles.
	// If the line does not meet the criteria, it is not added to the patient
	// collection,
	// and an error message is included in the return String.
	// The error message will indicate which line was in error and what the error
	// was.
	// expected line format
	// id,protein1,protein2, ... , protein4776

	// upload the current data file
	public void uploadFile(String fileName) {
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String readLine[];
			String line;
			while ((line = lineReader.readLine()) != null) {
				readLine = line.split(",");
				ArrayList<Double> genome = new ArrayList<Double>();
				String g;
				String result = readLine[0];
				String predict = readLine[1];
				String id = readLine[2];
				for (int i = 3; i < 4779; i++) {
					g = readLine[i];
					genome.add(Double.parseDouble(g));
				}
				Patient p = new Patient(result, predict, id, genome);
				PatientMap.put(id, p);
			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");
			try {
				lineReader = new BufferedReader(
						new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				String readLine[];
				while ((line = lineReader.readLine()) != null) {
					readLine = line.split(",");
					ArrayList<Double> genome = new ArrayList<Double>();
					String g;
					if (readLine.length == 4779) {
						String result = readLine[0];
						String predict = readLine[1];
						String id = readLine[2];
						for (int i = 3; i < 4779; i++) {
							g = readLine[i];
							genome.add(Double.parseDouble(g));
						}
						Patient p = new Patient(result, predict, id, genome);
						PatientMap.put(id, p);
					} else if (readLine.length == 4777) {
						String id = readLine[0];
						for (int i = 1; i < 4777; i++) {
							g = readLine[i];
							genome.add(Double.parseDouble(g));
						}
						if (!PatientMap.containsKey(id)) {
							Patient p = new Patient("unknown", "unknown", id, genome);
							PatientMap.put(id, p);
						} else {
							System.out.println("The number ID " + id + " is already in the system");
						}
					}
				}
			} catch (Exception e2) {
				System.err.println(
						"there was a problem with the file reader, try again.  either no such file or format error");
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
	}

	public String toString() {
		Patient p = new Patient();
		String g = "";
		for (String key : PatientMap.keySet()) {
			p = PatientMap.get(key);
			g += p.getId() + ", " + p.getGenome().get(3697) + ", " + p.getGenome().get(3258) + "\n";
		}
		return g;
	}
}
