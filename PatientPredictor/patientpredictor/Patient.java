package patientpredictor;

import java.util.ArrayList;

public class Patient {
	private String result;
	private String predict;
	private String id;
	private ArrayList<Double> genome = new ArrayList<Double>();
	
	public Patient() {
		result = "not set";
		predict = "not set";
		id = "not set";
		genome = null;
	}
	
	public Patient(String r, String p, String i, ArrayList<Double> g) {
		result = r;
		predict = p;
		id = i;
		genome = g;
	}

	//getters and setters 
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPredict() {
		return predict;
	}

	public void setPredict(String predict) {
		this.predict = predict;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Double> getGenome() {
		return genome;
	}

	public void setGenome(ArrayList<Double> genome) {
		this.genome = genome;
	}
	
	public String toString() {
		return id + " " + predict + " " + result + " " + genome.get(3697) + ", " + genome.get(2358) + "\n";
	}
}
