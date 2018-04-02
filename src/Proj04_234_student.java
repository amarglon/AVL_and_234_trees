import java.io.IOException;
import java.util.ArrayList;

public class Proj04_234_student implements Proj04_Dictionary {
	//TODO insert, traversals, transfers and rotations
	public Proj04_234_student () {
		root = null;
	}
	
	@Override
	public String search(int key) {
		Proj04_234Node traversal = root;
		while (traversal != null) {
			if (key == traversal.key1) {
				return traversal.val1;
			} else if (key == traversal.key2) {
				return traversal.val2;
			} else if (key == traversal.key3) {
				return traversal.val3;
			} else {
				traversal = getChild(key, traversal);
			}
		}
		return null;
	}
	
	public  Proj04_234Node getChild(int key, Proj04_234Node traversal) {
		if (traversal.childA == null) {
			return null;
		} 
		traversal = get(key, traversal);
		return traversal;
	}
	
	private Proj04_234Node get(int key, Proj04_234Node traversal) {
		Proj04_234Node[] childArray = {traversal.childA, traversal.childB, traversal.childC, traversal.childD};
		for (Proj04_234Node node : childArray) {
			if (key <= node.key1) {
				return node;
			}
			else if (key <= node.key2) {
				return node;
			}
			else if (key <= node.key3) {
				return node;
			}
		}
		return null;
	}
	
	//Might have trouble with root still being null? simple fix by making root = null into root.key1 = null
	@Override
	public void insert(int key, String value) throws IllegalArgumentException {
		if (root == null) {
			root.key1 = key;
			root.val1 = value;
		}
		if (search(key) != null) {
			throw new IllegalArgumentException("Attempt to insert a duplicate key '" + key + "'");
		}
		Proj04_234Node leaf = null;
		Proj04_234Node traversal = root;
		while (traversal != null) {
			leaf = traversal;
			traversal = getChild(key, traversal);
			insertHelper(key, value, leaf, null);
		}
	}
	private void insertHelper(int key, String value, Proj04_234Node leaf, Proj04_234Node right) {
		ArrayList<Proj04_234Node> path = pathFinder(key);
	}
	
	private ArrayList<Proj04_234Node> pathFinder(int key) {
		Proj04_234Node presentNode = root;
		ArrayList<Proj04_234Node> traversal = new ArrayList<>();
		/*while (node != null) {
			traversal.add(presentNode);
			if (node.key < presentNode.key) {
				presentNode = presentNode.left;
			} else if (node.key > presentNode.key) {
				presentNode = presentNode.right;
			} else {
				break;
			}
		}*/
		return traversal;
	}

	@Override
	public void delete(int key) { 
		try {
			deleteHelper(key);
		} catch (CannotDeleteException e) {
			e.printStackTrace();
		}
	}
	private void deleteHelper(int key) throws CannotDeleteException {
		throw new CannotDeleteException("Delete has not been defined for 234 trees");
	}
	
	class CannotDeleteException extends Exception {
		private static final long serialVersionUID = 1L;

		CannotDeleteException(String message){
			System.out.println(message);
		}
	}

	@Override
	public void genDebugDot(String filename) {
		try {
			Proj04_GenDotFile.gen(root, filename);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("genDebugDot(): File handling error Proj04_BST_student class");
		}
	}

	@Override
	public void printInOrder() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printPreOrder() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printPostOrder() {
		// TODO Auto-generated method stub

	}

	private Proj04_234Node root;
}
