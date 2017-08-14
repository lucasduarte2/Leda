package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {
	private int LLcounter = 0;
	private int LRcounter = 0;
	private int RRcounter = 0;
	private int RLcounter = 0;
	
	public int getLLcounter() {
		return LLcounter;
	}

	private void setLLcounter(int lLcounter) {
		LLcounter = lLcounter;
	}

	public int getLRcounter() {
		return LRcounter;
	}

	private void setLRcounter(int lRcounter) {
		LRcounter = lRcounter;
	}

	public int getRRcounter() {
		return RRcounter;
	}

	private void setRRcounter(int rRcounter) {
		RRcounter = rRcounter;
	}

	public int getRLcounter() {
		return RLcounter;
	}

	private void setRLcounter(int rLcounter) {
		RLcounter = rLcounter;
	}


	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if ((node == null) || (node.isEmpty()))
			return -1;

		int heightDifference = super.height((BSTNode<T>) node.getLeft()) - super.height((BSTNode<T>) node.getRight());

		return Math.abs(heightDifference);
	}
		
	protected void rebalance(BSTNode<T> node) {
		if ((node == null) || (node.isEmpty()))
			return;

		BSTNode<T> rotatedNode = rotation(node);

		if (rotatedNode.getParent() == null) {
			root = rotatedNode;
		}
	}
	
	protected void rebalanceUp(BSTNode<T> node) {
		if (node == null)
			return;

		if (calculateBalance(node) > 1) {
			rebalance(node);
		}
		rebalanceUp((BSTNode<T>) node.getParent());
	}

	private int calculateHeightDifference(BSTNode<T> leftNode, BSTNode<T> rightNode) {
		int heightLeftNode = recursiveHeight(leftNode, 0);
		int heightRightNode = recursiveHeight(rightNode, 0);

		return (heightLeftNode - heightRightNode);
	}

	protected void leftRotation(BSTNode<T> node) {
		Util.leftRotation(node);
	}

	protected void rightRotation(BSTNode<T> node) {
		Util.rightRotation(node);
	}
	@Override
	public void insert(T element) {
		if (element != null) {
			super.insert(element);
			BSTNode<T> node = search(element);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}
	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		super.remove(element);
		rebalanceUp((BSTNode<T>) node.getParent());
	}


	private BSTNode<T> rotation(BSTNode<T> node) {
		int heightDifference = calculateHeightDifference((BSTNode<T>) node.getLeft(), (BSTNode<T>) node.getRight());

		if (heightDifference >= 1) {
			int differenceChildren = calculateHeightDifference((BSTNode<T>) node.getLeft().getLeft(),
					(BSTNode<T>) node.getLeft().getRight());

			
			if (differenceChildren <= -1) {
				Util.leftRotation((BSTNode<T>) node.getLeft());
				setLRcounter(getLRcounter() + 1);
				return Util.rightRotation(node);
			} else {
				setRRcounter(getRRcounter() + 1); 
				return Util.rightRotation(node);
			}

			
		} else { 
			int differenceChildren = calculateHeightDifference((BSTNode<T>) node.getRight().getLeft(),
					(BSTNode<T>) node.getRight().getRight());

			
			if (differenceChildren >= 1) { 
				Util.rightRotation((BSTNode<T>) node.getRight());
				setRLcounter(getRLcounter() + 1);
				return Util.leftRotation(node);
			} else {
				setLLcounter(getLLcounter());
				return Util.leftRotation(node);
			}
		}
	}
}
