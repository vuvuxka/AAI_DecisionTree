import java.io.IOException;
import java.util.ArrayList;


public class Relation {
	
	public Relation(String n)
	{
		name = n;
		at = new ArrayList<OptAttribute>();
	}
	public Relation()
	{
		at = new ArrayList<OptAttribute>();
		data = new ArrayList<Example>();
	}
	
	public void addAtribute(OptAttribute a)
	{
		if(a != null && a.correct()) at.add(a);
	}
	public boolean check() {
		return !at.isEmpty() && !data.isEmpty();
	}
	public void setInformation(String[] s) throws IOException {
		int i = 0;
		if(s.length-1 != at.size()) throw new IOException();
		ArrayList<OptAttribute> d = new ArrayList<OptAttribute>();
		for(OptAttribute a: at)
		{
			d.add(a.setValue(s[i]));
			i++;
		}
		
		data.add(new Example(d, new OptAttribute(goal.getName(), goal.getOptions(), s[s.length-1]))); 
		
	}
	public static OptAttribute pluralityValue(ArrayList<Example> examples)
	{
		int[] ops = new int[examples.get(0).goal.getOptions().size()];
		for(int i = 0; i < examples.get(0).goal.getOptions().size(); i++) ops[i] = 0;
		for(Example e: examples)
		{
			for(int i = 0; i < examples.get(0).goal.getOptions().size(); i++)
				if(e.goal.getValue().equals(examples.get(i).goal.getOptions().get(i))) ops[i]++;
		}
		int max = 0;
		for(int i = 0; i < examples.get(0).goal.getOptions().size(); i++) if(ops[i] > ops[max]) max = i;
		return new OptAttribute(examples.get(0).goal.getName(), examples.get(0).goal.getOptions(), examples.get(0).goal.getOptions().get(max));
		
	}
	
	public class Example
	{
		public Example(ArrayList<OptAttribute> a, OptAttribute g)
		{
			atr = a;
			goal = g;
		}
		OptAttribute goal;
		public OptAttribute getGoal() {
			return goal;
		}
		ArrayList<OptAttribute> atr;
		public int hasAttribute(OptAttribute t) {
			int i = 0, ind = -1;
			for(OptAttribute a: this.atr)
			{
				if(a.getName().equals(t.getName())) { ind = i;}
				i++;
			}
			return ind;
		}
		public boolean hasSameAttribute(OptAttribute t) {
			boolean contains = false;
			for(OptAttribute a: this.atr)
				if(a.getName().equals(t.getName()) && a.getValue().equals(t.getValue())) contains = true;
			return contains;
		}
	}

	public static boolean sameClasification(ArrayList<Example> examples) {
		boolean same = true;
		int i = 1;
		while(same && i < examples.size())
		{
			same = same && examples.get(i).goal.getValue().equals(examples.get(i-1).goal.getValue());
			i++;
		}
		return same;
	}
	public static OptAttribute Clasification(ArrayList<Relation.Example> examples) {
		if(sameClasification(examples)) return examples.get(0).goal;
		return null;
	}
	public void addGoal() {
		OptAttribute t = at.get(at.size()-1);
		goal = t;
		at.remove(at.size()-1);		
	}
	//Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<OptAttribute> getAt() {
		return at;
	}
	public ArrayList<Example> getData() {
		return data;
	}

	private String name;
	private ArrayList<OptAttribute> at;
	private ArrayList<Example> data;
	private OptAttribute goal;
}
