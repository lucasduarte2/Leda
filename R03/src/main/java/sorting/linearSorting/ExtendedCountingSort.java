package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
  	public void sort(Integer[] array, int leftIndex, int rightIndex) {
  		if(array != null && leftIndex >= 0 && rightIndex < array.length && leftIndex <= rightIndex) {
  			extendedCountingSort(array, leftIndex, rightIndex);
  		}
  	}
  	
  	private void extendedCountingSort(Integer[] array, int leftIndex, int rightIndex) {
  		Integer maior = array[leftIndex];
  		Integer menor = array[leftIndex];
		Integer[] resultado = new Integer[array.length];
  		
  		for(int i = leftIndex + 1; i <= rightIndex; i++) {
  			if(maior.compareTo(array[i]) < 0) {
  				maior = array[i];
 			}
  			if(menor.compareTo(array[i]) > 0) {
  				menor = array[i];
  			}
  		}
  		
  		Integer range = maior - menor + 1;
  		int[] contagem = new int[range];
  		
  		for(int i = leftIndex; i <= rightIndex; i++) {
  			contagem[array[i] - menor]++;
  		}
  		
  		for(int i = 1; i < range; i++) {
  			contagem[i] += contagem[i - 1];
  		}
  		
  		for(int i = rightIndex; i >= leftIndex; i--) {
  			resultado[contagem[array[i] - menor] - 1] = array[i];
  			contagem[array[i] - menor]--;
  		}
  		
  		for(int i = leftIndex; i <= rightIndex; i++) {
  			array[i] = resultado[i - leftIndex];
  		}
  	
	}

}
