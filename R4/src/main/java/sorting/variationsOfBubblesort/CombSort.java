package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
  		if (array != null) {
  			if ((leftIndex < rightIndex) && (leftIndex >= 0)
  					&& (rightIndex <= array.length)) {
  				int gap = rightIndex - leftIndex;
  				boolean swapped = true;
  				while (gap > 1 || swapped) {
  					if (gap > 1)
  						gap = (int) (gap / 1.25);
  
  					int i = leftIndex;
  					swapped = false;
  					while (i + gap <= rightIndex) {
  						if (array[i].compareTo(array[i + gap]) > 0) {
  							Util.swap(array, i, i + gap);
  							swapped = true;
  						}
  						i++;
  					}
  				}
  			}
  		}
	}
}
