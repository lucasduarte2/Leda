package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		@Override
  	public void sort(Integer[] array, int leftIndex, int rightIndex) {
  		if(array != null && leftIndex >= 0 && rightIndex < array.length && leftIndex <= rightIndex) {
  			countingSort(array, leftIndex, rightIndex);
  		}
  	}
  	
  	private void countingSort(Integer[] array, int leftIndex, int rightIndex) {
  		Integer maior = array[leftIndex];
  		Integer[] resultado = new Integer[array.length];
  		
  		for(int i = leftIndex + 1; i <= rightIndex; i++) {
  			if(maior.compareTo(array[i]) < 0) {
  				maior = array[i];
  			}
  		}
  		
 		int[] contagem = new int[maior + 1];
  		
  		for(int i = leftIndex; i <= rightIndex; i++) {
  			contagem[array[i]]++;
  		}
  		
  		for(int i = 1; i <= maior; i++) {
  			contagem[i] += contagem[i - 1];
  		}
  		
  		for(int i = rightIndex; i >= leftIndex; i--) {
  			resultado[contagem[array[i]] - 1] = array[i];
  			contagem[array[i]]--;
 		}
  		
  		for(int i = leftIndex; i <= rightIndex; i++) {
  			array[i] = resultado[i - leftIndex];
  		}
	}

}
