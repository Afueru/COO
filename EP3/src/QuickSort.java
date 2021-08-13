public class QuickSort implements SortStrategy {
	public void sort (int ini, int fim, Produto [] produtos,Criterio criterio) {
		if(ini < fim) {

			int q = particiona(ini, fim, produtos, criterio);
				
			sort(ini, q, produtos, criterio);
			sort(q + 1, fim, produtos, criterio);
		}
	}
	private int particiona(int ini, int fim, Produto [] produtos, Criterio criterio){

		Produto x = produtos[ini];
		int i = (ini - 1);
		int j = (fim + 1);
		return criterio.seleciona(i, j, produtos, x);
	}
}