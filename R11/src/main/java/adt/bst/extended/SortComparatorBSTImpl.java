package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import util.Util;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em
 * suas funcionalidades e possui um metodo de ordenar um array dado como
 * parametro, retornando o resultado do percurso desejado que produz o array
 * ordenado.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;

	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		BSTImpl<T> bst = new BSTImpl<>();
		for (int i = 0; i < array.length; i++) {
			bst.insert(array[i]);
		}
		return bst.order();

	}

	@Override
	public T[] reverseOrder() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		reverseOrder(array, 0, this.getRoot());
		return array;
	}

	private int reverseOrder(T[] array, int index, BSTNode<T> node) {
		if (!node.isEmpty()) {
			index = reverseOrder(array, index, (BSTNode<T>) node.getRight());
			array[index] = (T) node.getData();
			index++;
			index = reverseOrder(array, index, (BSTNode<T>) node.getLeft());
		}
		return index;

	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public BSTNode<T> search(T element) {
		return search(this.getRoot(), element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node == null) {
			BSTNode<T> nodeNil = new BSTNode<T>();
			return nodeNil;
		} else if (node.isEmpty()) {
			return node;
		} else if (comparator.compare(node.getData(), element) == 0) {
			return node;
		} else if (comparator.compare(node.getData(), element) > 0) {
			return search((BSTNode<T>) node.getLeft(), element);
		} else {
			return search((BSTNode<T>) node.getRight(), element);
		}
	}

	public void insert(T element) {
		if (element != null) {
			this.insert(this.getRoot(), element);
		}

	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (this.comparator.compare(node.getData(), element) > 0) {
				this.insert((BSTNode<T>) node.getLeft(), element);
			} else if (this.comparator.compare(node.getData(), element) < 0) {
				this.insert((BSTNode<T>) node.getRight(), element);
			}
		}
	}

}
