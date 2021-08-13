public class InsertionSort implements SortStrategy {

	public void sort(int ini, int fim, Produto [] produtos, Criterio criterio) {
		for(int i = ini; i <= fim; i++){
			Produto x = produtos[i];				
			int j = (i - 1); 
			criterio.seleciona(j, produtos, x, ini);
		}
	}
}