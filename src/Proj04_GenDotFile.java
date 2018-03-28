import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Proj04_GenDotFile {

	public Proj04_GenDotFile() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void gen(Proj04_BSTNode root, String filename)
		throws IOException {
		
		String digraph = "digraph {";
		String endDigraph = "}";
		//traverse Proj03 tree
		//print instructions in dot syntax
		//output to filename (which already includes the .dot extension 
		File dotFile = new File(filename);
		PrintWriter writer = new PrintWriter(dotFile);
		
		writer.println(digraph);
		drawRecursively(writer, root);
		writer.println(endDigraph);
		
		writer.close();
		
		//print start of method name, ie tree {
		//call methods to add nodes and connections
		//end with closing bracket }
		
		
		
		//close file
	}
	
	public static void drawRecursively(PrintWriter writer, Proj04_BSTNode parent) {
		
		int left;
		int right;
		int parentValue;
		String writeLine;
		
		if (parent != null && parent.left != null) {
			left = parent.left.key;
			parentValue = parent.key;
			writeLine = " " + parentValue + " -> " + left + ";";
			writer.println(writeLine);
			drawRecursively(writer, parent.left);
		}
		
		if (parent != null && parent.right != null) {
			right = parent.right.key;
			parentValue = parent.key;
			writeLine = " " + parentValue + " -> " + right + ";";
			writer.printf(" %s", writeLine);
			drawRecursively(writer, parent.right);
		}
	}
	
	public static void addLeftChild(Proj04_BSTNode left) {
		//write code, visible left
	}
	
	public static void addRightChild(Proj04_BSTNode right) {
		//write code, visible right
	}
	
	public static void addNullChild(Proj04_BSTNode space) {
		//write code, invisible left or right
	}
	

}
