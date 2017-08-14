package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				insertFirst(element);
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!this.isEmpty()) {
			if (this.getData().equals(element)) {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());

			} else {
				this.getNext().remove(element);
			}
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void insertFirst(T element) {
		RecursiveDoubleLinkedListImpl<T> oldHead = new RecursiveDoubleLinkedListImpl<T>(this.getData(), this.getNext(),
				this.getPrevious());
		this.setData(element);
		this.setNext(oldHead);
		oldHead.setPrevious(this);
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> second = (RecursiveDoubleLinkedListImpl<T>) this.getNext();
			RecursiveDoubleLinkedListImpl<T> third = (RecursiveDoubleLinkedListImpl<T>) second.getNext();
			if (third != null) {
				third.setPrevious(this);
			}
			this.setNext(third);
			this.setData(second.getData());
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.getNext().isEmpty()) {
				this.setNext(null);
				this.setData(null);
			} else {
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).removeLast();
			}
		}
	}

	@Override
	public RecursiveDoubleLinkedListImpl<T> getNext() {
		return (RecursiveDoubleLinkedListImpl<T>) super.getNext();
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
