package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.getRoot());
	}

	protected int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		} else {
			return 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(this.getRoot(), element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty() || element == null) {
			return new BSTNode<T>();
		}

		if (node.getData().equals(element)) {
			return node;
		}

		if (node.getLeft() != null && element.compareTo(node.getData()) < 0) {
			return this.search((BSTNode<T>) node.getLeft(), element);
		} else {
			return this.search((BSTNode<T>) node.getRight(), element);
		}

	}

	@Override
	public void insert(T element) {
		if (element != null) {
			this.insert(root, element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		}
		if (element.compareTo(node.getData()) < 0) {
			this.insert((BSTNode<T>) node.getLeft(), element);
		} else if (element.compareTo(node.getData()) > 0) {
			this.insert((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return this.maximum(getRoot());
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		} else if (node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return this.minimum(getRoot());
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		} else if (node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);

		if (node.isEmpty())
			return null;

		if (!node.getRight().isEmpty()) {
			return minimum((BSTNode<T>) node.getRight());
		}

		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null && node.equals(parent.getRight())) {
			node = parent;
			parent = (BSTNode<T>) parent.getParent();
		}
		return parent;

	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);

		if (node.isEmpty())
			return null;

		if (!node.getLeft().isEmpty()) {
			return maximum((BSTNode<T>) node.getLeft());
		}

		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null && node.equals(parent.getLeft())) {
			node = parent;
			parent = (BSTNode<T>) parent.getParent();
		}
		return parent;

	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			remove(node);
		}
	}

	private void remove(BSTNode<T> node) {
		if (node.isEmpty())
			return;
		if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			BSTNode<T> newNode = sucessor(node.getData());
			node.setData(newNode.getData());
			remove(newNode);
		} else {
			BSTNode<T> newNode = (BSTNode<T>) node.getLeft();

			if (newNode.isEmpty()) {
				newNode = (BSTNode<T>) node.getRight();
			}

			node.setData(newNode.getData());
			node.setRight(newNode.getRight());
			node.setLeft(newNode.getLeft());

			if (!node.isEmpty() && node.getRight() != null) {
				node.getRight().setParent(node);
			}
			if (!node.isEmpty() && node.getLeft() != null) {
				node.getLeft().setParent(node);
			}
		}
	}

	@Override
	public T[] preOrder() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(array, 0, this.getRoot());
		return array;
	}

	private int preOrder(T[] array, int index, BSTNode<T> node) {
		if (!node.isEmpty()) {
			array[index] = (T) node.getData();
			index++;
			index = preOrder(array, index, (BSTNode<T>) node.getLeft());
			index = preOrder(array, index, (BSTNode<T>) node.getRight());
		}
		return index;
	}

	@Override
	public T[] order() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		order(array, 0, this.getRoot());
		return array;
	}

	private int order(T[] array, int index, BSTNode<T> node) {
		if (!node.isEmpty()) {
			index = order(array, index, (BSTNode<T>) node.getLeft());
			array[index] = (T) node.getData();
			index++;
			index = order(array, index, (BSTNode<T>) node.getRight());
		}
		return index;
	}

	@Override
	public T[] postOrder() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(array, 0, this.getRoot());
		return array;
	}

	private int postOrder(T[] array, int index, BSTNode<T> node) {
		if (!node.isEmpty()) {
			index = postOrder(array, index, (BSTNode<T>) node.getLeft());
			index = postOrder(array, index, (BSTNode<T>) node.getRight());
			array[index] = (T) node.getData();
			index++;
		}
		return index;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
