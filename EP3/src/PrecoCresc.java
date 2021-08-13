public class PrecoCresc implements Criterio {
	public void seleciona (int j, Produto [] produtos, Produto x, int ini) {
		while(j >= ini){
			if(x.getPreco() < produtos[j].getPreco()){
	
				produtos[j + 1] = produtos[j];
				j--;
			}
			else break;
		}

		produtos[j + 1] = x;
		return;
	}
	public int seleciona (int i, int j, Produto [] produtos, Produto x) {
		while (true) {
			
			do{ 
				j--;

			} while(produtos[j].getPreco() > x.getPreco());
			
			do{
				i++;

			} while(produtos[i].getPreco() < x.getPreco());
			if(i < j){
				Produto temp = produtos[i];
				produtos[i] = produtos[j]; 				
				produtos[j] = temp;
			}
			else return j;
		}
	}
}