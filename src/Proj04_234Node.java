/* Proj04_234Node
 *
 * Author: Russ Lewis
 *
 * Basically the same as the Proj04_234Node class, but expanded to hold three
 * keys and 4 child links.
 */

public class Proj04_234Node
{
	// this tells us how many keys are stored in the node.  In internal
	// nodes, it's redundant, but it's critical in leaf nodes.  And it's
	// very helpful in the internal nodes, as well, so we might as well
	// keep it.
	//
	// Possible values: 1,2,3

	public int numKeys;

	// these are the keys themselves; ignore fields which are not
	// valid, according to numKeys above.

	public int key1,key2,key3;

	// each key is paired with a matching value variable (which must not
	// be null for a valid key)

	public String val1,val2,val3;


	// if this is a leaf node, then *ALL* of these pointers must be
	// null.  If not, then the number of non-nulls should be exactly
	// one more than the number of keys above.

	public Proj04_234Node childA,childB,childC,childD;
}

