/* Proj04_TestDotFile_Test*
 *
 * Class to exercise the GenDotFile class.  This hard-codes construction of
 * a single BST, and then gives that tree to GenDotFile.
 *
 * This class intentionally does *NOT* dynamically build a tree, since that
 * would effectively be a solution to the project!  So it hard-codes the
 * structure.
 *
 * However, students should be aware that we will use a *DIFFERENT* tree when
 * we grade their code - and thus, they *MUST* write a version of GenDotFile
 * which will work with *ANY* tree.
 *
 * Should the student write more testcases?  They are welcome to, but I don't
 * think it's necessary.  Instead, they should *USE* GenDotFile, throughout
 * their debug of their main BST code, to show themselves the shapes of the
 * trees they are building!
 */

import java.io.*;

public class Proj04_TestDotFile_Tree3
{
	/* main
	 *
	 * Test driver for the GenDotFile class.
	 *
	 * Builds a tree of BSTNode objects (with no wrapper BST class).
	 * The shape and content of the tree is hard-coded.  It then
	 * passes the entire tree to GenDotFile.gen().
	 */
	public static void main(String[] args)
	{
		Proj04_BSTNode all[] = new Proj04_BSTNode[32+1];
		for (int i=1; i<all.length; i++)
			all[i] = new Proj04_BSTNode(i, NAMES[i-1]);

		/* I randomly generated a sequence of insertions, and used
		 * my own AVL code to generate the dot file, which I used
		 * to hand-construct the tree below.
		 */

		// ----- layer 1 -----
		Proj04_BSTNode root = all[16];

		all[16].left        = all[ 6];
		all[16].right       = all[22];
		all[16].height      = 5;

		// ----- layer 2 -----
		all[ 6].left        = all[ 2];
		all[ 6].right       = all[11];
		all[ 6].height      = 4;

		all[22].left        = all[18];
		all[22].right       = all[29];
		all[22].height      = 4;

		// ----- layer 3 -----
		all[ 2].left        = all[ 1];
		all[ 2].right       = all[ 4];
		all[ 2].height      = 2;

		all[11].left        = all[ 8];
		all[11].right       = all[14];
		all[11].height      = 3;

		all[18].left        = all[17];
		all[18].right       = all[20];
		all[18].height      = 2;

		all[29].left        = all[25];
		all[29].right       = all[31];
		all[29].height      = 3;

		// ----- layer 4 -----

		   // 1 is leaf

		all[ 4].left        = all[ 3];
		all[ 4].right       = all[ 5];
		all[ 4].height      = 1;

		all[ 8].left        = all[ 7];
		all[ 8].right       = all[10];
		all[ 8].height      = 2;

		all[14].left        = all[13];
		all[14].right       = all[15];
		all[14].height      = 2;

		   // 17 is leaf

		all[20].left        = all[19];
		all[20].right       = all[21];
		all[20].height      = 1;

		all[25].left        = all[23];
		all[25].right       = all[27];
		all[25].height      = 2;

		all[31].left        = all[30];
		all[31].right       = all[32];
		all[31].height      = 1;

		// ----- layer 5 -----

		   //  3 is leaf
		   //  5 is leaf
		   //  7 is leaf

		all[10].left        = all[ 9];
		all[10].height      = 1;

		all[13].left        = all[12];
		all[13].height      = 1;

		   // 15 is leaf
		   // 19 is leaf
		   // 21 is leaf

		all[23].right       = all[24];
		all[23].height      = 1;

		all[27].left        = all[26];
		all[27].right       = all[28];
		all[27].height      = 1;

		   // 30 is leaf
		   // 32 is leaf

		try {
			Proj04_GenDotFile.gen(root, "sanity_check.dot");
			System.out.println("Test done, gen() returned without throwing an exception.");
			System.out.println("Hopefully, it actually generated a file.");
		} catch (IOException e) {
			System.out.println("ERROR: Could not open the file for writing.");
		}
	}


	public static final String[] NAMES =
	    { "Fred",     "Wilma",    "Alice",    "Bob",
	      "Algernon", "Charlie",  "Breetai",  "Gloval",
	      "Fido",     "Fido",     "Fido Again", "Can't Stop Saying Fido",
	      "Russ",     "Emily",    "Eric",     "Rose",
	      "Valar",    "Sindar",   "Vanyar",   "Noldor",
	      "Andor",    "Cairhien", "Sheinar",  "Tear",
	      "USA",      "UK",       "UAE",      "UFP",
	      "Tree",     "List",     "Array",    "Pile of Index Cards"};
}

