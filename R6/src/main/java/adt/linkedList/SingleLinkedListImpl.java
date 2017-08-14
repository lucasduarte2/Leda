package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.getHead().isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> auxiliar = this.getHead();
		while (!auxiliar.isNIL()) {
			size++;
			auxiliar = auxiliar.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		if (element == null || isEmpty()) {
			return null;
		}
		SingleLinkedListNode<T> auxiliar = this.getHead();

		while (!auxiliar.isNIL()) {
			if (auxiliar.getData().equals(element)) {
				return element;
			}
			auxiliar = auxiliar.getNext();
		}
		return null;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			SingleLinkedListNode<T> auxiliar = this.head;

			while (!auxiliar.isNIL()) {
				auxiliar = auxiliar.getNext();

			}
			auxiliar.setData(element);
			auxiliar.setNext(new SingleLinkedListNode<>());

		}
	}

	@Override
	public void remove(T element) {
		if (element == null || this.isEmpty()) {
			return;
		}
		if (this.getHead().getData().equals(element)) {
			this.setHead(this.getHead().getNext());
		}
		SingleLinkedListNode<T> auxiliar = this.getHead();
		SingleLinkedListNode<T> auxAnterior = null;
		while (!auxiliar.isNIL() && !auxiliar.getData().equals(element)) {
			auxAnterior = auxiliar;
			auxiliar = auxiliar.getNext();
		}

		if (!auxiliar.isNIL()) {
			auxAnterior.setNext(auxiliar.getNext());
		}

	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		SingleLinkedListNode<T> auxiliar = this.getHead();
		if (auxiliar == null) {
			return null;
		}

		int indice = 0;
		while (!auxiliar.isNIL()) {
			array[indice] = auxiliar.getData();
			auxiliar = auxiliar.getNext();
			indice++;
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
