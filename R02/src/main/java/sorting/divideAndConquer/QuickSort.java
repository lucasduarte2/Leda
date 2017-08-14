package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < 0) {
			leftIndex = 0;
		}
		if (rightIndex > array.length - 1) {
			rightIndex = array.length - 1;
		}
		if (!(array == null && leftIndex > rightIndex)) {
			quickSort(array, leftIndex, rightIndex);
		}
	}

	private void quickSort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int positionPivot = particiona(array, leftIndex, rightIndex);
			quickSort(array, leftIndex, positionPivot - 1);
			quickSort(array, positionPivot + 1, rightIndex);
		}
	}

	private int particiona(T[] array, int leftIndex, int rightIndex) {
		T pivor = array[leftIndex];
		int positionPivot = leftIndex;
		for (int j = positionPivot + 1; j <= rightIndex; j++) {
			if (array[j].compareTo(pivor) < 0) {
				positionPivot++;
				Util.swap(array, positionPivot, j);
			}
		}

		Util.swap(array, leftIndex, positionPivot);

		return positionPivot;

	}
}
