package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> aux = this.root;
		// Pesquisa o local
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (aux.getForward(i).getKey() < key) {
				aux = aux.getForward(i);
			}
			// Guarda o caminho
			update[i] = aux;
		}
		aux = aux.getForward(0);
		if (aux.getKey() == key) {
			aux.setValue(newValue);
		} else {
			aux = new SkipListNode<T>(key, height, newValue);
			// Altera os ponteiros
			for (int i = 0; i < height; i++) {
				aux.forward[i] = update[i].getForward(i);
				update[i].forward[i] = aux;
			}
		}
	}

	@Override
	public void remove(int key) {
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> aux = this.root;
		// Pesquisa o local
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (aux.getForward(i).getKey() < key) {
				aux = aux.getForward(i);
			}
			// Guarda o caminho
			update[i] = aux;
		}
		aux = aux.getForward(0);
		if (aux.getKey() == key) {
			// Altera os ponteiros
			for (int i = 0; i < this.maxHeight; i++) {
				if (update[i].getForward(i) != aux) {
					break;
				}
				update[i].forward[i] = aux.getForward(i);
			}
		}
	}

	@Override
	public int height() {

		SkipListNode<T> node = this.root;
		int height = 0;
		while (!node.getForward(0).equals(NIL)) {
			if (node.getForward(0).height() > height) {
				height = node.getForward(0).height();
			}
			node = node.getForward(0);
		}
		return height;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> x = this.root;
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			// Anda ate o ultimo no com mesma altura
			while (x.getForward(i).getKey() < key) {
				x = x.getForward(i);
			}
		}
		x = x.getForward(0);
		if (x.getKey() == key) {
			return x;
		} else {
			// Chave nao existe
			return null;
		}
	}

	@Override
	public int size() {
		return size(this.root.getForward(0));
	}

    private int size(SkipListNode<T> node) {
		if(node == NIL)
			return 0;
		else
			return 1 + size(node.getForward(0));
	}

	@Override
	public SkipListNode<T>[] toArray() {
		@SuppressWarnings("unchecked")
		// +2 pois deve adicionar os nos sentinelas
		SkipListNode<T>[] array = new SkipListNode[this.size() + 2];
		SkipListNode<T> x = this.root;
		int index = 0;
		// A condicao ira quebrar depois do sentinela
		while (x != null) {
			array[index++] = x;
			x = x.getForward(0);
		}
		return array;
	}
}
