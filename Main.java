import java.util.Scanner;



public class Main {
	
	public static void main(String args[]) {
		boolean first = true;
		
		Relation relation = new Relation();
		while(!relation.check()) 
		{ 
			System.out.print("File name: ");
			String name = scan.nextLine().trim();
			Reader reader = new Reader(name);
			reader.getInfor(relation);
			if (!first) System.out.println("Problem with the data. Remember the last attribute will be used as the goal.");;
			first = false;
		}
		TreeNode tree = DTree.decisionTreeLearning(relation.getData(), relation.getAt(), null); // Construct the tree
		tree.printTree(relation); // Print the tree
	}

	static Scanner scan = new Scanner(System.in);
}
