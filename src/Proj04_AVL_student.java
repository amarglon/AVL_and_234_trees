import java.io.IOException;
import java.util.ArrayList;

public class Proj04_AVL_student implements Proj04_Dictionary {

	public Proj04_AVL_student() {

		root = null;
	}

	@Override
	public String search(int key) {
		Proj04_BSTNode presentNode = root;
		while (presentNode != null) {
			if (key < presentNode.key) {
				presentNode = presentNode.left;
			} else if (key > presentNode.key) {
				presentNode = presentNode.right;
			} else {
				return presentNode.value;
			}
		}
		return null;
	}

	@Override
	public void insert(int key, String value) throws IllegalArgumentException {
		// Throws IllegalArgumentException if the key already exists in the tree
		if (root == null) {
			root = new Proj04_BSTNode(key, value);
		} else {
			if (search(key) != null) { // check if it exists in table
				throw new IllegalArgumentException("Attempt to insert a duplicate key '" + key + "'");
			} else { // create new node, recursively call helper method until new node is placed
				Proj04_BSTNode newNode = new Proj04_BSTNode(key, value);
				insertHelper(newNode, root);
				rebalanceTree(newNode);
			}
		}
	}

	private void insertHelper(Proj04_BSTNode key, Proj04_BSTNode parent) {
		if (key.key < parent.key) {
			if (parent.left == null) {
				parent.left = key;
			} else {
				insertHelper(key, parent.left);
			}
		} else {
			if (parent.right == null) {
				parent.right = key;
			} else {
				insertHelper(key, parent.right);
			}
		}
	}

	@Override
	public void delete(int key) {
		
		Proj04_BSTNode parentNode = null;
		Proj04_BSTNode presentNode = root;

		if (search(key) != null) { 
			while (presentNode != null) {
				if (key < presentNode.key) {
					parentNode = presentNode;
					presentNode = presentNode.left;
				} else if (key > presentNode.key) {
					parentNode = presentNode;
					presentNode = presentNode.right;
				} else
					break;
			}

			// Case 1: current has no left child
			if (presentNode.left == null) {
				// Connect the parent with the right child of the current node
				if (parentNode == null) {
					root = presentNode.right;
				} else {
					if (key < parentNode.key)
						parentNode.left = presentNode.right;
					else
						parentNode.right = presentNode.right;
				}
				rebalanceTree(parentNode);
			} else {
				// Case 2: The current node has a left child
				// Locate the rightmost node in the left subtree of
				// the current node and also its parent
				Proj04_BSTNode parentOfRightMost = presentNode;
				Proj04_BSTNode rightMost = presentNode.left;

				while (rightMost.right != null) {
					parentOfRightMost = rightMost;
					rightMost = rightMost.right; // Keep going to the right
				}

				// Replace the element in current by the element in rightMost
				presentNode.key = rightMost.key;

				// Eliminate rightmost node
				if (parentOfRightMost.right == rightMost)
					parentOfRightMost.right = rightMost.left;
				else
					// Special case: parentOfRightMost == current
					parentOfRightMost.left = rightMost.left;
				rebalanceTree(parentOfRightMost);
			}
		}
	}

	private void rebalanceTree(Proj04_BSTNode node) {
		// method updates heights of all nodes in the path
		// of the recently inserted node
		// The method also manages rotations based on heights
		ArrayList<Proj04_BSTNode> traversal = pathFinder(node);
		Proj04_BSTNode parentNode;
		for (int currentNode = traversal.size() - 1; currentNode > -1; currentNode--) {

			updateHeights(traversal.get(currentNode));
			if (traversal.get(currentNode) == root) {
				parentNode = null;
			} else {
				parentNode = traversal.get(currentNode - 1);
			}
			assignRotation(traversal, currentNode, parentNode);
		}
	}

	private ArrayList<Proj04_BSTNode> pathFinder(Proj04_BSTNode node) {
		Proj04_BSTNode presentNode = root;
		ArrayList<Proj04_BSTNode> traversal = new ArrayList<>();
		while (node != null) {
			traversal.add(presentNode);
			if (node.key < presentNode.key) {
				presentNode = presentNode.left;
			} else if (node.key > presentNode.key) {
				presentNode = presentNode.right;
			} else {
				break;
			}
		}
		return traversal;
	}

	private void updateHeights(Proj04_BSTNode node) {
		// height is incremented depending on whether the
		// inserted node has children or not. If it has
		// two children, it takes 1 + the height of the
		// larger child
		if (node.left != null && node.right != null) {
			if ((node.left).height > (node.right).height) {
				node.height = (node.left).height + 1;
			} else {
				node.height = (node.right).height + 1;
			}
		} else if (node.left != null && node.right == null) {
			node.height = (node.left).height + 1;
		} else if (node.left == null && node.right != null) {
			node.height = (node.right).height + 1;
		} else {
			node.height = 0;
		}
	}

	private int rotationManager(Proj04_BSTNode node) {
		int caseManager;
		if (node.right == null)
			caseManager = 0 - node.height;
		else if (node.left == null)
			caseManager = node.height;
		else
			caseManager = (node.right).height - (node.left).height;
		return caseManager;
	}

	private void assignRotation(ArrayList<Proj04_BSTNode> traversal, int currentNode, Proj04_BSTNode parentNode) {
		switch (rotationManager(traversal.get(currentNode))) {
		case -2:
			if (rotationManager(traversal.get(currentNode).left) < 1) {
				singleRightRotation(traversal.get(currentNode), parentNode);
			} else {
				LeftRightRotation(traversal.get(currentNode), parentNode);
			}
			break;
		case +2:
			if (rotationManager(traversal.get(currentNode).right) > -1) {
				singleLeftRotation(traversal.get(currentNode), parentNode);
			} else {
				RightLeftRotation(traversal.get(currentNode), parentNode);
			}
		}
	}

	private void singleLeftRotation(Proj04_BSTNode node, Proj04_BSTNode parent) {
		Proj04_BSTNode rightNode = node.right;
		if (node == root) {
			root = rightNode;
		} else {
			if (parent.left == node) {
				parent.left = rightNode;
			} else {
				parent.right = rightNode;
			}
		}
		node.right = rightNode.left;
		rightNode.left = node;
		updateHeights(node);
		updateHeights(rightNode);
	}

	private void singleRightRotation(Proj04_BSTNode node, Proj04_BSTNode parent) {
		Proj04_BSTNode leftNode = node.left;
		if (node == root) {
			root = leftNode;
		} else {
			if (parent.left == node) {
				parent.left = leftNode;
			} else {
				parent.right = leftNode;
			}
		}
		node.left = leftNode.right;
		leftNode.right = node;
		updateHeights(node);
		updateHeights(leftNode);
	}

	private void LeftRightRotation(Proj04_BSTNode node, Proj04_BSTNode parent) {
		// the node's left child, and the left child's right child (grand child)
		Proj04_BSTNode leftNode = node.left;
		Proj04_BSTNode rightChild = leftNode.right;

		if (node == root) {
			root = rightChild;
		} else {
			if (parent.left == node) {
				parent.left = rightChild;
			} else {
				parent.right = rightChild;
			}
		}

		node.left = rightChild.right;
		leftNode.right = rightChild.left;
		rightChild.left = leftNode;
		rightChild.right = node;

		updateHeights(node);
		updateHeights(leftNode);
		updateHeights(rightChild);
	}

	private void RightLeftRotation(Proj04_BSTNode node, Proj04_BSTNode parent) {
		Proj04_BSTNode rightNode = node.right;
		Proj04_BSTNode leftChild = rightNode.left;

		if (node == root) {
			root = leftChild;
		} else {
			if (parent.left == node) {
				parent.left = leftChild;
			} else {
				parent.right = leftChild;
			}
		}

		node.right = leftChild.left;
		rightNode.left = leftChild.right;
		leftChild.left = node;
		leftChild.right = rightNode;

		updateHeights(node);
		updateHeights(rightNode);
		updateHeights(leftChild);
	}

	@Override
	public void printInOrder() {
		// left root right
		// recursion
		if (root == null) {
			return;
		} else {
			inOrderHelper(root);
		}
	}

	private void inOrderHelper(Proj04_BSTNode node) {
		if (node == null) {
			return;
		} else {
			inOrderHelper(node.left);
			System.out.print(" " + node.key + ":'" + node.value + "'");
			inOrderHelper(node.right);
		}
	}

	@Override
	public void printPreOrder() {
		// root left right
		if (root == null) {
			return;
		} else {
			preOrderHelper(root);
		}
	}

	private void preOrderHelper(Proj04_BSTNode node) {
		if (node == null) {
			return;
		} else {
			System.out.print(" " + node.key + ":'" + node.value + "'");
			preOrderHelper(node.left);
			preOrderHelper(node.right);
		}
	}

	@Override
	public void printPostOrder() {
		// left right root
		// recursion
		if (root == null) {
			return;
		} else {
			postOrderHelper(root);
		}
	}

	private void postOrderHelper(Proj04_BSTNode node) {
		if (node == null) {
			return;
		} else {
			postOrderHelper(node.left);
			postOrderHelper(node.right);
			System.out.print(" " + node.key + ":'" + node.value + "'");
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

	private Proj04_BSTNode root;

}
