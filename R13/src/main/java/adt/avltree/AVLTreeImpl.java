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

	public void insert(T element) {
		if (element != null) {
			super.insert(element);
			BSTNode<T> node = search(element);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}

	public void remove(T element) {
		BSTNode<T> node = search(element);
		super.remove(element);
		rebalanceUp((BSTNode<T>) node.getParent());
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (!node.isEmpty()) {
			return super.height((BSTNode<T>) node.getLeft()) - super.height((BSTNode<T>) node.getRight());
		}
		return 0;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);

		if (balance < -1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) > 0) {
				Util.leftRotation((BSTNode<T>) node.getLeft());
			}
			Util.rightRotation(node);
		} else if (balance > 1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
				Util.rightRotation((BSTNode<T>) node.getRight());
			}
			Util.leftRotation(node);
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			rebalance(node);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}

}
