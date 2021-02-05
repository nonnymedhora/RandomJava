/**
 * 
 */
package org.bawaweb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Navroz
 * 
 * ref1
 * https://github.com/rafalio/dancing-links-java/blob/master/src/dlx/DancingLinks.java
 *
 */
public class DancingSudoku {
	
	private static final int DIM = 9;
	
	private class DancingNode {
		
		protected DancingNode left;
		protected DancingNode right;
		protected DancingNode up;
		protected DancingNode down;
		
		protected ColumnNode  col;
		
		public DancingNode() {
			left = right = up = down = this;
		}
		
		public DancingNode(ColumnNode aCol) {
			this();
			this.col = aCol;
		}
		
		
		public DancingNode hookDown(DancingNode aNode) {
			assert(this.col == aNode.col);
			aNode.down 		= this.down;
			aNode.down.up 	= aNode;
			aNode.up		= this;
			this.down		= aNode;
			return aNode;
		}
		
		public DancingNode hookRight(DancingNode aNode) {
			aNode.right 		= this.right;
			aNode.right.left	= aNode;
			aNode.left			= this;
			this.right			= aNode;
			return aNode;
		}
		
		public void unLinkLr() {
			this.left.right = this.right;
			this.right.left = this.left;
			updatesCount++;
		}
		
		public void unLinkUd() {
			this.up.down = this.down;
			this.down.up = this.up;
			updatesCount++;
		}
		
		public void reLinkLr() {
			this.left.right = this.right.left;
			this.right.left = this;
			updatesCount++;
		}
		
		public void reLinkUd() {
			this.up.down = this.down.up;
			this.down.up = this;
			updatesCount++;
		}
	}
	
	private class ColumnNode extends DancingNode {
		private int size;		//# of 1s in the column
		private String name;
		
		public ColumnNode(String n){
			super();
			this.size = 0;
			this.name = n;			
			col = this;
		}
		
		public void cover(){
			unLinkLr();
			for(DancingNode i = this.down; i != this; i = i.down) {
				for(DancingNode j = this.right; j != this; j = j.right) {
					j.unLinkUd();
					j.col.size--;
				}
			}
			header.size--;
		}
		
		public void uncover() {
			for(DancingNode i = this.up; i != this; i = i.up) {
				for(DancingNode j = this.left; j != this; j = j.left) {
					j.col.size++;
					j.reLinkUd();
				}
			}
			reLinkLr();
			header.size++;
		}
	}
	
	private interface SolutionHandler {
		void handleSolution(List<DancingNode> solution);
	}
	
	private class DefaultHandler implements SolutionHandler {
	    public void handleSolution(List<DancingNode> answer){
	        for(DancingNode n : answer){
	            String ret = "";
	            ret += n.col.name + " ";
	            DancingNode tmp = n.right;
	            while (tmp != n){
	                ret += tmp.col.name + " ";
	                tmp = tmp.right;
	            }
	            System.out.println(ret);
	        }
	    }
	}

	private int updatesCount = 0;
	private int solutionsCount = 0;
	protected ColumnNode header;
	private SolutionHandler h;

	private List<DancingNode> solution;
	
	

	private class DancingLinks {
		public DancingLinks(boolean[][] grid) {
			this(grid,new DefaultHandler());
		}

		public DancingLinks(boolean[][] grid, SolutionHandler handler) {
			header = generateDLXBoard(grid);
			h = handler;
		}
		
		// main algorithm
		private void search(int k) {
			if(header.right == header) {	//all cols covered/removed
				//handler.handleSolution(solution);
				solutionsCount++;
			} else {
				ColumnNode col = selectCoulumnNodeHeuristic();
				col.cover();
				
				for( DancingNode row = col.down; row != col; row = row.down) {
					solution.add(row);
					
					for(DancingNode cell = row.right; cell != row; cell = cell.right) {
						cell.col.cover();
					}
					search(k+1);
					
					row = solution.remove(solution.size()-1);
					col = row.col;
					
					for(DancingNode cell = row.left; cell != row; cell = cell.left) {
						cell.col.uncover();
					}
				}
				col.uncover();
				
			}
		}
		
		
		private ColumnNode selectCoulumnNodeHeuristic() {
			int min = Integer.MIN_VALUE;
			ColumnNode colNode = null;
			for( ColumnNode c = (ColumnNode)header.right; c != header; c = (ColumnNode)c.right ) {
				if( c.size < min ) {
					min = c.size;
					colNode = c;
				}
			}
			return colNode;
		}
		
		private ColumnNode selectColumnNodeRandomly() {
			ColumnNode aNode = null;
			ColumnNode ptr = (ColumnNode) header.right;
			int col = 1;
			while( ptr != header ) {
				if(Math.random() <= 1/(double)col) {
					aNode = ptr;
				}
				col++;
				ptr = (ColumnNode) ptr.right;
			}
			return aNode;
		}
		
		private ColumnNode selectColumn(int n) {
			int num = n % header.size;
			ColumnNode aNode = (ColumnNode) header.right;
			for(int i = 0; i < num; i++) {
				aNode = (ColumnNode) aNode.right;
			}
			return aNode;
		}
		
		private ColumnNode generateDLXBoard(boolean[][] grid) {
			
			final int COLS = grid[0].length;
			final int ROWS = grid.length;
			
			ColumnNode header = new ColumnNode("header");
			List<ColumnNode> colNodes = new ArrayList<ColumnNode>();
			
			for( int i = 0; i < COLS; i++ ) {
				ColumnNode aColNode = new ColumnNode(Integer.toString(i));
				colNodes.add( aColNode );
				header = (ColumnNode) header.hookRight( aColNode );
			}
			header = header.right.col;
			
			for( int i = 0; i < ROWS; i++ ) {
				DancingNode prev = null;
				for( int j = 0; j < COLS; j++) {
					if( grid[i][j] == true ) {
						ColumnNode aCol = colNodes.get(j);
						
						DancingNode newNode = new DancingNode(aCol);
						if( prev == null ) {
							prev = newNode;
						}
						
						aCol.up.hookDown(newNode);
						prev = prev.hookRight(newNode);
						aCol.size++;
					}
				}
			}
			header.size = COLS;
			return header;
		}
		
		
		public void runSolver() {
			solutionsCount = 0;
			updatesCount = 0;
			solution = new LinkedList<DancingNode>();
			search(0);
		}
	}
	
	private class Node {
		Node 	previous;
		Node 	next;
		int 	value;
		
		public Node(Node pvs, Node nxt, int val) {
			super();
			this.previous = pvs;
			this.next = nxt;
			this.value = val;
		}
		
		
		
	}
	LinkedList<Node> colList = new LinkedList<Node>();
	LinkedList<Node> rowList = new LinkedList<Node>();
	

	public static void main(String[] args) {
		
		DancingSudoku ds = new DancingSudoku();
		
		LinkedList<Node>	c = ds.getColList();
		LinkedList<Node> r = ds.getRowList();
		
		c = ds.initialize(c);
		r = ds.initialize(r);
		
		/*System.out.println(r.getFirst().value);
		System.out.println(r.getLast().next.value);
		
		ds.printList(c);
		System.out.println("+++++++++++++++++++++++++++++");*/
		c=ds.initializeChain(7);
//		ds.printList(c);
//		System.out.println(c.getFirst().value);
//		System.out.println(c.getLast().next.value);
		
		
		

		c=ds.initializeChain(1);
		ds.printList(c);
		c=ds.initializeChain(2);
		ds.printList(c);
		c=ds.initializeChain(3);
		ds.printList(c);
		c=ds.initializeChain(4);
		ds.printList(c);
		c=ds.initializeChain(5);
		ds.printList(c);
		c=ds.initializeChain(6);
		ds.printList(c);
		c=ds.initializeChain(7);
		ds.printList(c);
		c=ds.initializeChain(8);
		ds.printList(c);
		c=ds.initializeChain(9);
		ds.printList(c);
		
		
		

	}
	
	private LinkedList<Node> initializeChain(final int start) {
		LinkedList<Node> aList = new LinkedList<Node>();
		int val = start+1;
		Node first = new Node(null,null,start);	
		aList.addFirst(first);
		for(int i = 1; val <= DIM; i++,val++){
			Node aNode = new Node(aList.get(i-1),null,val);
			aList.add(aNode);
			aList.get(i-1).next = aNode;
		}
		
		if (start!=1) {
			val = 1;
			aList.add(new Node(aList.get(aList.size() - 1), null, val));
			val = 2;
			for (int j = 1; j < start - 1; j++, val++) {
				Node aNode = new Node(aList.get(aList.size() - 1), null, val);
				aList.add(aNode);
				aList.get(aList.size() - 1).next = aNode;

			}
			aList.get(aList.size() - 1).next = first;
		}
		return aList;
		
	}

	private LinkedList<Node> initialize(LinkedList<Node> list) {
		int i = 0;
		final Node first = new Node(null, null, 1);
		for (i = 1; i <= DIM; i++) {
			if (i == 1) {				
				list.addFirst(first);
			} else {
				Node aNode = new Node(list.get(i-2),null,i);
				list.add(aNode);
				list.get(i-2).next = aNode;
				
			}
		}
		list.get(i-2).next = list.get(0);
		return list;
	}

	public LinkedList<Node> getColList() {
		return colList;
	}

	public void setColList(LinkedList<Node> list) {
		this.colList = list;
	}

	public LinkedList<Node> getRowList() {
		return rowList;
	}

	public void setRowList(LinkedList<Node> list) {
		this.rowList = list;
	}
	
	private void printList(List<Node> list) {
		String listStr = "";
		Iterator<Node> it = list.iterator();
		while(it.hasNext()) {
			listStr += it.next().value + " ";
		}
		System.out.println(listStr);
//		System.out.println("===============================");
	}

}
