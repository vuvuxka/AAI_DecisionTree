public class TreeNode
{
  public TreeNode ()  {  }
 
  public TreeNode (OptAttribute d)  {  data = d; }

  public void add (TreeNode child)
  {
	  TreeNode[] newChildren = new TreeNode[ children.length + 1 ];
      System.arraycopy( children, 0, newChildren, 0, children.length );
      newChildren[children.length] = child;
      children = newChildren;
  }
  
   public TreeNode remove (int index)
  {
    if ( index < 0 || index >= children.length ) throw new IllegalArgumentException("Cannot remove element with index " + index + " when there are " + children.length + " elements.");
    TreeNode node = children[index];
    TreeNode[] newChildren = new TreeNode[ children.length - 1 ];
    if ( index > 0 )
    {
      System.arraycopy( children, 0, newChildren, 0, index );
    }
    if ( index != children.length - 1 )
    {
      System.arraycopy( children, index + 1, newChildren, index, children.length - index - 1 );
    }
    children = newChildren;
    
    return node;
  }
  
  public void printTree(Relation r)
  {
	  System.out.println("\t\t*** " + r.getName().toUpperCase());
	  this.auxPrintTree(0);
  }
  
  private void auxPrintTree(int j)
  {
	  if(this.children.length == 0) System.out.print(" -> " + this.data.getName() + ": " + this.data.getValue().toUpperCase() + "\n");
	  else 
	  {
		  System.out.print("\n");
		  int i = 0;
		  for(TreeNode t: this.children)
		  {
			  for(int k = 0; k <= j; k++) System.out.print("\t");
			  System.out.print(this.data.toString() + data.getOptions().get(i));
			  j++;
			  t.auxPrintTree(j);
			  j--;
			  i++;
		  }
	  }  
	}
  	private TreeNode[] children = new TreeNode[0];
	private OptAttribute data;
}