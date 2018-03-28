/* Proj04_BSTNode
 *
 * Author: Russ Lewis
 *
 * Basically the same as the Proj03_BSTNode class, but expanded to include a
 * 'height' field.  (In the Splay Tree, the height field is ignored.)
 */

public class Proj04_BSTNode
{
	public int    key;
	public String value;

	public int    height;

	public Proj04_BSTNode left,right;


	/* constructor
	 *
	 * Parameters: int,String
	 *
	 * Generates a single BSTNode (with no children), with the key/value
	 * pair given.
	 */
	public Proj04_BSTNode(int key, String value)
	{
		if (value == null)
			throw new IllegalArgumentException("BSTNode: The 'value' parameter was null");

		this.key    = key;
		this.value  = value;
		this.height = 0;

		// the left,right nodes default to 'null' (Java defaults)
	}
}

