import java.util.ArrayList;


public class DTree { //The class that makes the decision tree

	
	public static TreeNode decisionTreeLearning(ArrayList<Relation.Example> examples, ArrayList<OptAttribute> attributes, 
			ArrayList<Relation.Example> parents_examples)
	{
		if(examples.isEmpty()) return new TreeNode(Relation.pluralityValue(parents_examples));
		else if(Relation.sameClasification(examples)) return new TreeNode(Relation.Clasification(examples));
		else if(attributes.isEmpty()) return new TreeNode(Relation.pluralityValue(examples));
		else
		{
			OptAttribute max = attributes.get(0);
			for(OptAttribute a: attributes) 
			{
				if(importance(a, examples) >= importance(max, examples)) max = a;
			}
			TreeNode tree = new TreeNode(max);
			for(String t: max.getOptions())
			{
				ArrayList<Relation.Example> exs = getExamples(max.setValue(t), examples);
				@SuppressWarnings("unchecked")
				ArrayList<OptAttribute> at = (ArrayList<OptAttribute>) attributes.clone();
				at.remove(max);
				TreeNode subt = decisionTreeLearning(exs, at, examples);
				tree.add(subt);
				
			}
			return tree;
		}
		
	}
	private static double importance(OptAttribute at, ArrayList<Relation.Example> examples) {
		int n = at.getOptions().size(), m = examples.get(0).getGoal().getOptions().size();
		int[][] matrix = new int[n][m];
		for(int i = 0; i < n; i++) for(int j = 0; j < m; j++)
			{matrix[i][j] = 0;}
		for(Relation.Example e: examples) // We make the matrix (explain in the archive)
		{
			int i = e.hasAttribute(at);
			int n2 = at.getOptions().indexOf(e.atr.get(i).getValue()), m2 = e.getGoal().getOptions().indexOf(e.getGoal().getValue());
			if(i != -1 && 0 <= n2 && n2 < n && 0 <= m2 && m2 < m ) matrix[n2][m2]++;
		}
		double total = 0, val = 0;
		double[] sum1 = new double[n];
		double[] sum2 = new double[m];
		for(int i = 0; i < m ; i++) sum2[i] = 0;
		for(int i = 0; i < n; i++)
		{
			sum1[i] = 0;
			for(int j = 0; j < m; j++) {sum1[i]+= matrix[i][j]; sum2[j]+= matrix[i][j]; }
			total += sum1[i]; 
		}
		for(int i = 0; i < n; i++)
		{
			double[] row = intArrToDoubleArr(matrix[i]);
			val += (sum1[i]/total)*entropy(row);
		}
		return entropy(sum2) - val; 
	}
	private static double entropy(double ... ts) //Calculates the entropy
	{
		double sum = 0;
		double total = 0;
		for(double t: ts) {
			total += t;
		}
		for(double t: ts) {
			if(t != 0) sum += (t/total)*(Math.log(t/total)/Math.log(ts.length));
		}
		return -sum;
	}
	// getExamples returns {e: e in examples and e.A = v_k}
	private static ArrayList<Relation.Example> getExamples(OptAttribute t, ArrayList<Relation.Example> examples) {
		ArrayList<Relation.Example> exs = new ArrayList<Relation.Example>();
		for(Relation.Example e: examples)
		{
			if(e.hasSameAttribute(t)) exs.add(e);
		}
		return exs;
	}
	
	private static double[] intArrToDoubleArr(int[] arr) {
		double[] result = new double[arr.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = arr[i];
		}
		return result;
	}
}
