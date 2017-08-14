package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < 0) {
			leftIndex = 0;
		}
		if (rightIndex > array.length - 1) {
			rightIndex = array.length - 1;
		}
		if (!(array == null && leftIndex > rightIndex)) {
			mergeSort(array, leftIndex, rightIndex);
		}

	}

	private void mergeSort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int average = (leftIndex + rightIndex) / 2;
			mergeSort(array, leftIndex, average);
			mergeSort(array, average + 1, rightIndex);
			merge(array, leftIndex, average, rightIndex);
		}

	}

	private void merge(T[] array, int leftIndex, int media, int rightIndex) {
		@SuppressWarnings("unchecked")
		T[] arrayHelper = (T[]) new Comparable[rightIndex + 1];
		for (int i = leftIndex; i <= rightIndex; i++) {
			arrayHelper[i] = array[i];
		}

		int i = leftIndex;
		int j = media + 1;
		int k = leftIndex;
		while (i <= media && j <= rightIndex) {
			if (arrayHelper[i].compareTo(arrayHelper[j]) < 0) {
				array[k] = arrayHelper[i];
				i++;

			} else {
				array[k] = arrayHelper[j];
				j++;
			}
			k++;
		}

		while (i <= media) {
			array[k] = arrayHelper[i];
			i++;
			k++;
		}

		while (j <= rightIndex) {
			array[k] = arrayHelper[j];
			j++;
			k++;
		}

	}
}
