package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
  		if (array != null) {
  			if ((leftIndex < rightIndex) && (leftIndex >= 0)
  					&& (rightIndex <= array.length)) {
  				int pos = leftIndex+1;
  				while (pos <= rightIndex) {
  					if (pos == leftIndex || array[pos-1].compareTo(array[pos]) <= 0)
  						pos++;
  					else {
  						Util.swap(array, pos, pos - 1);
  						pos--;
  					}
  
  				}
  
  			}
  		}
	}
}
