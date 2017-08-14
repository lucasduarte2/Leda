package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (this.isFull()) {
			throw new HashtableOverflowException();
		}
		if (element != null) {
			if (this.indexOf(element) == -1) {
				HashFunctionQuadraticProbing<T> hashFunction = (HashFunctionQuadraticProbing<T>) this.getHashFunction();
				
				int probe = 0;
				
				while (probe < this.capacity()) {
					int index = hashFunction.hash(element, probe);
					
					if (this.table[index] == null || this.table[index].equals(deletedElement)) {
						this.table[index] = element;
						this.elements++;
						return;
					} else {
						super.COLLISIONS++;
						probe++;
					}
					
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			int index = this.indexOf(element);
			if (index != -1) {
				this.table[index] = deletedElement;
				this.elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		if (this.indexOf(element) != -1) {
			return element;
		} else {
			return null;
		}
	}

	@Override
	public int indexOf(T element) {
		if (element != null && !this.isEmpty()) {
			int probe = 0;
			HashFunctionQuadraticProbing<T> hashFunction = (HashFunctionQuadraticProbing<T>) this.getHashFunction();
			
			while (probe < this.capacity()) {
				int index = hashFunction.hash(element, probe);
				if (this.table[index] != null && this.table[index].equals(element)) {
						return index;
				} else {
					probe++;
				}
			}
		}
		return -1;
	}
}
