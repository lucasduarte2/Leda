package adt.avltree;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return getLLcounter();
	}

	@Override
	public int LRcount() {
		return getLRcounter();
	}

	@Override
	public int RRcount() {
		return getRRcounter();
	}

	@Override
	public int RLcount() {
		return getRLcounter();
	}
	

	@Override
	public void fillWithoutRebalance(T[] array) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
