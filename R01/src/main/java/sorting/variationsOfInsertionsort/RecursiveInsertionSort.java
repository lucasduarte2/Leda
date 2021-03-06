package sorting.variationsOfInsertionsort;

import sorting.AbstractSorting;

public class RecursiveInsertionSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * Implementacaoo RECURSIVA do insertion sort. Para isso, tente definir o 
	 * caso base do algoritmo e depois o caso recursivo, que reduz o problema 
	 * para uma entrada menor em uma chamada recursiva. Seu algoritmo deve 
	 * ter complexidade quadratica O(n^2).
	 * 
	 * Restrições:
	 *  - voce nao pode utilizar arry auxiliar
	 *  - voce pode utilizar variaveis temporarias
	 *  - voce nao pode declarar novos atributos na classe
	 *  - para as trocas no array, utilize o metodo Util.swap
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
			if (array != null) {
  			if ((leftIndex < rightIndex) && (leftIndex >= 0)
  					&& (rightIndex <= array.length)) {
  				if ( leftIndex >= array.length - 1 )
  			        return;
  			    int minIndex = leftIndex;
  			    for ( int index = leftIndex + 1; index < array.length; index++ )
  			    {
  			        if (array[index].compareTo(array[minIndex]) < 0 )
  			            minIndex = index;
  			    }			    
  			    Util.swap(array, leftIndex, minIndex);
  			    
  			    sort(array, leftIndex + 1, rightIndex);
  			}
  		}
	}

}
