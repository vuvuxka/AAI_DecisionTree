

import java.util.ArrayList;



public class OptAttribute{


	public OptAttribute(String na, ArrayList<String> o) {
		name = na;
		options = o;
	}
	public OptAttribute(String na, ArrayList<String> o, String val) {
		name = na.trim();
		options = o;
		value = val.trim();
	}
	

	public boolean correct() {
		return !options.isEmpty() && name.length() > 0;
	}

	
	public String toString()
	{
		return "Attribute " + this.name + ": ";
	}

	//Getters and Setters
	public ArrayList<String> getOptions() {
		return options;
	}
	public OptAttribute setValue(String s) { //Generates a new instance of the same Attribute, but this
		String val = null;					// with the value
		if(!s.trim().equals("?") && options.contains(val)) 
		{
			val = s.trim();
		}
		return new OptAttribute(this.name, this.options, s);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}

	private ArrayList<String> options;
	private String value;
	private String name;

}
