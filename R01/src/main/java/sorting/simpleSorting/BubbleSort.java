package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
 public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {
  
  	@Override
  	public void sort(T[] array, int leftIndex, int rightIndex) {
  		if (array.length > 0 
  				&& leftIndex >= 0 
  				&& rightIndex < array.length 
  				&& leftIndex < rightIndex) {
  			
  			for (int j = leftIndex; j <= rightIndex; j++) {
  				for (int i = leftIndex + 1; i <= rightIndex; i++) {
  					if (array[i - 1].compareTo(array[i]) > 0) {
  						Util.swap(array, i - 1, i);
 					}
  				}
  			}
  		}
  	}
 }
