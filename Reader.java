import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Reader { // Reads the relations from the arff file.
	public Reader(String fileName) 
	{
		try {
			file = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Could not access the file ("
					+ fileName + ")");
			System.exit(0);
		}
	}
	public void getInfor(Relation r)
	{
		try 
		{
			String n;
			do {n = file.readLine().toLowerCase();} while(!n.toLowerCase().contains("@relation "));
			r.setName(n.substring("@relation ".length()));
			boolean data = false;
			while(!data)
			{
				do {n = file.readLine().toLowerCase();} while(!n.toLowerCase().contains("@attribute ") && !n.toLowerCase().contains("@data"));
				data = n.toLowerCase().contains("@data");
				if (!data) 
				{
					String t = n.substring("@attribute ".length());
					OptAttribute a = null;
					if(t.contains("{") && !data)
					{
						String name = t.substring(0, t.indexOf('{')).trim();
						t = t.substring(t.indexOf('{')+1, t.indexOf('}'));
						String[] ops = t.split(",");
						ArrayList<String> da = new ArrayList<String>();
						for(String o: ops)
						{
							o.trim();
							da.add(o.trim());
						}
						a = new OptAttribute(name, da);
					}
					else {throw new IOException();}
					r.addAtribute(a);
				}
			}
			r.addGoal();
			String l = file.readLine();
			while(l != null)
			{
				r.setInformation(l.toLowerCase().split(","));
				l = file.readLine();
			}
		} catch (IOException e) {
			System.out.println("Problem reading the file. ");
			System.exit(0);
		}
	}
	
	
	
	
	private BufferedReader file;
}
